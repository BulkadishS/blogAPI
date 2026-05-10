package com.bulkadishs.blog.repository.impl;

import com.bulkadishs.blog.model.Role;
import com.bulkadishs.blog.model.User;
import com.bulkadishs.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        // Транзакция
        return jdbcTemplate.execute((Connection con) -> {
            con.setAutoCommit(false);

            try (Statement stmt = con.createStatement()) {
                stmt.execute("BEGIN");
                System.out.println("Transaction for saving user was made successfully!");
                String INSERT_USER_QUERY="INSERT INTO users (username, password) VALUES(?,?)";

                try (PreparedStatement psUser = con.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                    psUser.setString(1, user.getUsername());
                    psUser.setString(2, user.getPassword());
                    psUser.executeUpdate();

                    try (ResultSet rs = psUser.getGeneratedKeys()) {
                        if (rs.next()) {
                            user.setId(rs.getLong(1));
                        }
                    }
                }
                String INSERT_USER_ROLE="INSERT INTO user_roles (user_id, role_id) " +
                        "VALUES (?, (SELECT id FROM roles WHERE NAME = 'USER'))";

                try (PreparedStatement psRole = con.prepareStatement(INSERT_USER_ROLE)) {
                    psRole.setLong(1, user.getId());
                    psRole.executeUpdate();
                }

                stmt.execute("COMMIT");

            } catch (Exception e) {
                try (Statement rollbackStmt = con.createStatement()) {
                    rollbackStmt.execute("ROLLBACK");
                }
                throw new RuntimeException(e);
            } finally {
                con.setAutoCommit(true);
            }
            return user;
        });
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

    public Optional<User> findByUsername(String username) {
        String GET_USER_BY_USERNAME_QUERY="SELECT * FROM users WHERE username=?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(GET_USER_BY_USERNAME_QUERY, userRowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        String GET_USER_ROLES= "SELECT r.name FROM roles r " +
               "JOIN user_roles ur ON r.id = ur.role_id " +
               "WHERE ur.user_id = ?";

        List<Role> roles = jdbcTemplate.query(GET_USER_ROLES, (rs, rowNum) ->
                Role.valueOf(rs.getString("name")), user.getId());
        user.setRoles(roles);

        return Optional.of(user);
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
