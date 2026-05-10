package com.bulkadishs.blog.repository;

import com.bulkadishs.blog.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {
    User save(User user);
    User findById(Long id);
    Optional<User> findByUsername(String username);
    int deleteById(Long id);
    List<User> findAll();
}
