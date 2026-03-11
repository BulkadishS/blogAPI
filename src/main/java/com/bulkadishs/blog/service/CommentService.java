package com.bulkadishs.blog.service;

import com.bulkadishs.blog.entity.CommentEntity;
import com.bulkadishs.blog.entity.PostEntity;
import com.bulkadishs.blog.entity.UserEntity;
import com.bulkadishs.blog.model.Comment;
import com.bulkadishs.blog.repository.CommentRepository;
import com.bulkadishs.blog.repository.PostRepository;
import com.bulkadishs.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Comment createComment(CommentEntity comment, Long userId, Long postId) {
        UserEntity user = userRepository.findById(userId).get();
        PostEntity post = postRepository.findById(postId).get();
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(LocalDate.now());

        return Comment.toModel(commentRepository.save(comment));
    }

    public Long delete(Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
