package com.bulkadishs.blog.model;

import java.time.LocalDate;

public class Comment {
    private Long id;
    private User author;
    private Post post;
    private String content;
    private LocalDate createdAt;

    public Comment(Long id, User author, Post post, String content, LocalDate createdAt) {
        this.id = id;
        this.author = author;
        this.post = post;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Comment() {}

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
