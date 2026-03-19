package com.bulkadishs.blog.controller;

import com.bulkadishs.blog.dto.CommentDto;
import com.bulkadishs.blog.model.Comment;
import com.bulkadishs.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentSerivce) {
        this.commentService = commentSerivce;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(
            @RequestBody Comment comment,
            @RequestParam Long postId,
            @RequestParam Long userId) {
        CommentDto savedCommentDto = commentService.createComment(comment, postId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCommentDto);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComents() {
        List<CommentDto> allComments = commentService.getAll();
        return ResponseEntity.ok(allComments);
    }

    @GetMapping(params = "id")
    public ResponseEntity<CommentDto> getOneComment(@RequestParam Long id) {
        CommentDto foundComment = commentService.getOne(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(foundComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}