package com.bulkadishs.blog.repository;

import com.bulkadishs.blog.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

// репозиторий работает только с бд
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
