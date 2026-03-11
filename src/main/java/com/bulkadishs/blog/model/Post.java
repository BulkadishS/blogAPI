package com.bulkadishs.blog.model;

import com.bulkadishs.blog.entity.PostEntity;

import java.time.LocalDate;

public class Post {
    private Long id;
    private String content;
    private LocalDate createdAt;
    public Post() {}

    public static Post toModel(PostEntity post) {
        Post model = new Post();
        model.setId(post.getId());
        model.setContent(post.getContent());
        model.setCreatedAt(post.getCreatedAt());
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
