package com.bulkadishs.blog.controller;

import com.bulkadishs.blog.dto.UserDto;
import com.bulkadishs.blog.model.User;
import com.bulkadishs.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDto> registration(@Valid @RequestBody User user) {
        UserDto savedUserDto = userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
    }

    @GetMapping(params = "id")
    public ResponseEntity<UserDto> getOneUser(@RequestParam Long id) {
        UserDto foundUser = userService.getOne(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(foundUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAll();
        return ResponseEntity.ok(allUsers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
