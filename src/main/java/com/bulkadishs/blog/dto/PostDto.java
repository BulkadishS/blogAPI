package com.bulkadishs.blog.dto;

import com.bulkadishs.blog.model.Post;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostDto {
    private Long id;
    private UserDto author;
    private String content;
    private LocalDate createdAt;
//    private List<CommentDto> comments;

    public PostDto(Long id, UserDto author, String content, LocalDate createdAt) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static PostDto from(Post post) {
        UserDto author = null;
        if (post.getAuthor() != null) {
            author = UserDto.from(post.getAuthor());
        }

        return new PostDto(
          post.getId(),
          author,
          post.getContent(),
          post.getCreatedAt()
        );

    }

//    public List<CommentDto> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<CommentDto> comments) {
//        this.comments = comments;
//    }

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
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
