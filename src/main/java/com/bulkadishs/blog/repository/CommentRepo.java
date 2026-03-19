package com.bulkadishs.blog.repository;

import com.bulkadishs.blog.model.Comment;

import java.util.List;

public interface CommentRepo {
    Comment save(Comment comment);
    Comment findById(Long id);
    int deleteById(Long id);
    List<Comment> findAll();
}
