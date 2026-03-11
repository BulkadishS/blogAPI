package com.bulkadishs.blog.controller;

import com.bulkadishs.blog.entity.PostEntity;
import com.bulkadishs.blog.model.PostRequest;
import com.bulkadishs.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostEntity post,
                                     @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(postService.createPost(post, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("400 bad request");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePost(@PathVariable Long id,
                                     @RequestBody PostRequest request) {
        try {
            return ResponseEntity.ok(postService.updatePost(request.getNewContent(), id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("400 bad request");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(postService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("400 bad request");
        }
    }
}
