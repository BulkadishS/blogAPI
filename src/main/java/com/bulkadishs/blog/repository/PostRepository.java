package com.bulkadishs.blog.repository;

import com.bulkadishs.blog.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

}
