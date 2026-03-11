package com.bulkadishs.blog.controller;

import com.bulkadishs.blog.entity.UserEntity;
import com.bulkadishs.blog.exception.UserAlreadyExistException;
import com.bulkadishs.blog.exception.UserNotFoundException;
import com.bulkadishs.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// контроллер работает с запросами и с ответами
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.register(user);
            return ResponseEntity.ok().body("successfully saved user in repo");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("400 bad request");
        }
    }

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(userService.getOne(id));

        } catch (UserNotFoundException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {

            return ResponseEntity.badRequest().body("400 bad request");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(userService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("400 bad request");
        }
    }
}
