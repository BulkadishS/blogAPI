package com.bulkadishs.blog.repository.impl;

import com.bulkadishs.blog.model.Comment;
import com.bulkadishs.blog.model.Post;
import com.bulkadishs.blog.model.User;
import com.bulkadishs.blog.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

@Repository
public class CommentRepoImpl implements CommentRepo {
    private final RowMapper<Comment> commentRowMapper = (rs, rowNum) -> {
        User author = new User();
        Post post = new Post();
        author.setId(rs.getLong("user_id"));
        author.setUsername(rs.getString("username"));

        post.setId(rs.getLong("post_id"));

        return new Comment(
                rs.getLong("id"),
                author,
                post,
                rs.getString("content"),
                rs.getTimestamp("created_at").toLocalDateTime().toLocalDate()
        );
    };

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Comment save(Comment comment) {
        String INSERT_COMMENT_QUERY="INSERT INTO comments (user_id, post_id, content, created_at) VALUES(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_COMMENT_QUERY, new String[]{"id"});
            ps.setLong(1, comment.getAuthor().getId());
            ps.setLong(2, comment.getPost().getId());
            ps.setString(3, comment.getContent());
            ps.setObject(4, comment.getCreatedAt());

            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            comment.setId(keyHolder.getKey().longValue());
        }

        return comment;
    }

    @Override
    public Comment findById(Long id) {
        String GET_COMMENT_BY_ID_QUERY=
                "SELECT c.*, u.username " +
                        "FROM comments c " +
                        "JOIN users u ON c.user_id = u.id " +
                        "JOIN posts p ON c.post_id = p.id " +
                        "WHERE c.id=?";

        try {
            return jdbcTemplate.queryForObject(GET_COMMENT_BY_ID_QUERY, commentRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        String DELETE_COMMENT_BY_ID_QUERY="DELETE FROM comments WHERE id=?";

        try {
            return jdbcTemplate.update(DELETE_COMMENT_BY_ID_QUERY, id);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<Comment> findAll() {
        String GET_COMMENTS_QUERY=
                "SELECT c.*, u.username " +
                        "FROM comments c " +
                        "JOIN users u ON c.user_id = u.id " +
                        "JOIN posts p ON c.post_id = p.id";

        List<Comment> comments = jdbcTemplate.query(GET_COMMENTS_QUERY, commentRowMapper);
        return (comments != null) ? comments : Collections.emptyList();
    }
}
