package com.bulkadishs.blog.repository;

import com.bulkadishs.blog.model.User;
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
public class UserRepoImpl implements UserRepo {
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("password")
    );

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        String INSERT_USER_QUERY="INSERT INTO users (username, password) VALUES(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {

            PreparedStatement ps = con.prepareStatement(INSERT_USER_QUERY, new String[]{"id"});
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            user.setId(keyHolder.getKey().longValue());
        }

        return user;
    }

    @Override
    public User findById(Long id) {
        String GET_USER_BY_ID_QUERY="SELECT * FROM users WHERE id=?";

        try {
            return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY, userRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findByUsername(String username) {
        String GET_USER_BY_USERNAME_QUERY="SELECT * FROM users WHERE username=?";

        try {
            return jdbcTemplate.queryForObject(GET_USER_BY_USERNAME_QUERY, userRowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        String DELETE_USER_BY_ID_QUERY="DELETE FROM users WHERE id=?";

        try {
            return jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, id);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<User> findAll() {
        String GET_USERS_QUERY="SELECT * FROM users";

        List<User> users = jdbcTemplate.query(GET_USERS_QUERY, userRowMapper);
        return (users != null) ? users : Collections.emptyList();
    }
}
