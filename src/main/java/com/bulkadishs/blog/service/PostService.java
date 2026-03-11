package com.bulkadishs.blog.service;

import com.bulkadishs.blog.entity.PostEntity;
import com.bulkadishs.blog.entity.UserEntity;
import com.bulkadishs.blog.model.Post;
import com.bulkadishs.blog.repository.PostRepository;
import com.bulkadishs.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(PostEntity post, Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        post.setUser(user);
        post.setCreatedAt(LocalDate.now());
        return Post.toModel(postRepository.save(post));
    }

    public Post updatePost(String newContent, Long id) {
        PostEntity post = postRepository.findById(id).get();
        post.setContent(newContent);
        return Post.toModel(postRepository.save(post));
    }

    public Long delete(Long id) {
        postRepository.deleteById(id);
        return id;
    }
}
