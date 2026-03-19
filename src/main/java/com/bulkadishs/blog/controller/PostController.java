package com.bulkadishs.blog.controller;

import com.bulkadishs.blog.dto.PostDto;
import com.bulkadishs.blog.model.Post;
import com.bulkadishs.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody Post post,
                                              @RequestParam Long userId) {

        PostDto savedPostDto = postService.createPost(post, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPostDto);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> allPosts = postService.getAll();
        return ResponseEntity.ok(allPosts);
    }

    @GetMapping(params = "id")
    public ResponseEntity<PostDto> getOnePost(@RequestParam Long id) {
        PostDto foundPost = postService.getOne(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(foundPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
