package com.bulkadishs.blog.repository.impl;

import com.bulkadishs.blog.model.Post;
import com.bulkadishs.blog.model.User;
import com.bulkadishs.blog.repository.PostRepo;
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
public class PostRepoImpl implements PostRepo {
    private final RowMapper<Post> postRowMapper = (rs, rowNum) -> {

            User author = new User();
            author.setId(rs.getLong("user_id"));
            author.setUsername(rs.getString("username"));

            return new Post(
            rs.getLong("id"),
            author,
            rs.getString("content"),
            rs.getTimestamp("created_at").toLocalDateTime().toLocalDate()
            );
    };

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Post save(Post post) {
        String INSERT_POST_QUERY="INSERT INTO posts (user_id, content, created_at) VALUES(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_POST_QUERY, new String[]{"id"});
            ps.setLong(1, post.getAuthor().getId());
            ps.setString(2, post.getContent());
            ps.setObject(3, post.getCreatedAt());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            post.setId(keyHolder.getKey().longValue());
        }

        return post;
    }

    @Override
    public Post findById(Long id) {
        String GET_POST_BY_ID_QUERY=
                "SELECT p.*, u.username " +
                        "FROM posts p " +
                        "JOIN users u ON p.user_id = u.id " +
                        "WHERE p.id=?";

        try {
            return jdbcTemplate.queryForObject(GET_POST_BY_ID_QUERY, postRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        String DELETE_POST_BY_ID_QUERY="DELETE FROM posts WHERE id=?";

        try {
            return jdbcTemplate.update(DELETE_POST_BY_ID_QUERY, id);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<Post> findAll() {
        String GET_POSTS_QUERY=
                "SELECT p.*, u.username " +
                        "FROM posts p " +
                        "JOIN users u ON p.user_id = u.id";

        List<Post> posts = jdbcTemplate.query(GET_POSTS_QUERY, postRowMapper);
        return (posts != null) ? posts : Collections.emptyList();
    }
}
