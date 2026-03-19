package com.bulkadishs.blog.service;

import com.bulkadishs.blog.dto.PostDto;
import com.bulkadishs.blog.exception.ResourceNotFoundException;
import com.bulkadishs.blog.model.Post;
import com.bulkadishs.blog.model.User;
import com.bulkadishs.blog.repository.PostRepo;
import com.bulkadishs.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {
    private PostRepo postRepo;
    private UserRepo userRepo;

    @Autowired
    public PostService(PostRepo postRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public PostDto createPost(Post post, Long userId) {
        User user = userRepo.findById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("Unable to find post with author id: " + userId);
        }

        post.setAuthor(user);
        post.setCreatedAt(LocalDate.now());
        return PostDto.from(postRepo.save(post));
    }

    public PostDto getOne(Long id) {
        Post foundPost = postRepo.findById(id);

        if (foundPost == null) {
            throw new ResourceNotFoundException("Post not found with this id: " + id);
        }

        return PostDto.from(foundPost);
    }

    public List<PostDto> getAll() {
        List<Post> allPosts = postRepo.findAll();
        return allPosts.stream()
                .map(PostDto::from)
                .toList();
    }

    public void delete(Long id) {
        if (postRepo.findById(id) == null) {
            throw new ResourceNotFoundException("Unable to delete! Post with id:" + id + " not found");
        }
        postRepo.deleteById(id);
    }
}
