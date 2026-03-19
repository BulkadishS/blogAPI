package com.bulkadishs.blog.dto;

import com.bulkadishs.blog.model.Comment;

import java.time.LocalDate;

public class CommentDto {
    private Long id;
    private UserDto author;
    private PostDto post;
    private String content;
    private LocalDate createdAt;

    public CommentDto(Long id, UserDto author, PostDto post, String content, LocalDate createdAt) {
        this.id = id;
        this.author = author;
        this.post = post;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentDto from(Comment comment) {
        UserDto author = null;
        PostDto post = null;
        if (comment.getAuthor() != null) {
            author = UserDto.from(comment.getAuthor());
        }

        if (comment.getPost() != null) {
            post = PostDto.from(comment.getPost());
        }

        return new CommentDto(
                comment.getId(),
                author,
                post,
                comment.getContent(),
                comment.getCreatedAt()
        );
    }

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
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
