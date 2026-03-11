package com.bulkadishs.blog.repository;

import com.bulkadishs.blog.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
}
