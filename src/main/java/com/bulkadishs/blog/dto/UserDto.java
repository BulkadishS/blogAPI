package com.bulkadishs.blog.dto;

import com.bulkadishs.blog.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDto {
    private Long id;
    private String username;
//    private List<PostDto> posts;
//    private List<CommentDto> comments;

    public UserDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public static UserDto from(User user) {
        return new UserDto(user.getId(), user.getUsername());
    }

//    public List<CommentDto> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<CommentDto> comments) {
//        this.comments = comments;
//    }

//    public List<PostDto> getPosts() {
//        return posts;
//    }

//    public void setPosts(List<PostDto> posts) {
//        this.posts = posts;
//    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
