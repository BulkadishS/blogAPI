package com.bulkadishs.blog.model;

public class User {

    private Long id;
    private String username;
    private String password;

//    private List<Post> posts;
//
//    private List<Comment> comments;

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User() {}

//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }

//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return  username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
