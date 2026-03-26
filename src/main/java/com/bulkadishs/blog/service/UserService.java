package com.bulkadishs.blog.service;

// сервис работает с логикой уже

import com.bulkadishs.blog.dto.UserDto;
import com.bulkadishs.blog.model.User;
import com.bulkadishs.blog.exception.ResourceAlreadyExistException;
import com.bulkadishs.blog.exception.ResourceNotFoundException;
import com.bulkadishs.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepo userRepo;
    private PasswordEncoderService passwordEncoderService;

    @Autowired
    public UserService(UserRepo userRepository, PasswordEncoderService passwordEncoderService) {
        this.userRepo = userRepository;
        this.passwordEncoderService = passwordEncoderService;
    }

    public UserDto register(User user) {

        // чтобы проверить защиту от рейс кондишена, просто закомментируй этот if
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new ResourceAlreadyExistException("User with this name already exists!");
        }

        String hashPassword = passwordEncoderService.encode(user.getPassword());
        User saveToUser = new User();
        saveToUser.setUsername(user.getUsername());
        saveToUser.setPassword(hashPassword);

        try {
            User savedUser = userRepo.save(saveToUser);
            return UserDto.from(savedUser);
        } catch (DuplicateKeyException ex) {
            throw new ResourceAlreadyExistException("Username is already taken!");
        }
    }

    public UserDto getOne(Long id) {
        User foundUser = userRepo.findById(id);

        if (foundUser == null) {
            throw new ResourceNotFoundException("User not found with this id: " + id);
        }

        return UserDto.from(foundUser);
    }

    public List<UserDto> getAll() {
        List<User> allUsers = userRepo.findAll();
        System.out.println("GET: all users: " + allUsers);
        return allUsers.stream()
                .map(UserDto::from)
                .toList();
    }

    public void delete(Long id) {
        if (userRepo.findById(id) == null) {
            throw new ResourceNotFoundException("Unable to delete! User with id: " + id + " not found");
        }
        userRepo.deleteById(id);
    }
}
