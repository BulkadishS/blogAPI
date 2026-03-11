package com.bulkadishs.blog.model;

import com.bulkadishs.blog.entity.CommentEntity;

import java.time.LocalDate;

public class Comment {
    private Long id;
    private String content;
    private LocalDate createdAt;

    public Comment(){}

    public static Comment toModel(CommentEntity comment) {
        Comment model = new Comment();
        model.setId(comment.getId());
        model.setContent(comment.getContent());
        model.setCreatedAt(comment.getCreatedAt());

        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
