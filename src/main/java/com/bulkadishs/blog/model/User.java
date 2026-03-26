package com.bulkadishs.blog.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    private Long id;
    @NotBlank(message = "Username can't be empty")
    @Size(min = 1, message = "Username length can't be lower than 1")
    @Size(max = 40, message = "Username length can't be higher than 40")
    private String username;
    @NotBlank(message = "Password can't be empty")
    @Size(min = 1, message = "Password length can't be lower than 1")
    private String password;

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User() {}

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
