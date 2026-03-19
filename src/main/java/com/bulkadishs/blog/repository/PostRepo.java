package com.bulkadishs.blog.repository;

import com.bulkadishs.blog.model.Post;

import java.util.List;

public interface PostRepo {
    Post save(Post post);
    Post findById(Long id);
    int deleteById(Long id);
    List<Post> findAll();
}
