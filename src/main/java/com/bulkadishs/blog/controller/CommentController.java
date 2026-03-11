package com.bulkadishs.blog.controller;

import com.bulkadishs.blog.entity.CommentEntity;
import com.bulkadishs.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity createComment(
            @RequestBody CommentEntity comment,
            @RequestParam Long userId,
            @RequestParam Long postId) {
        try {
            return ResponseEntity.ok(commentService.createComment(comment, userId, postId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("400 bad request");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(commentService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("400 bad request");
        }
    }
}
