package com.bulkadishs.blog.service;

import com.bulkadishs.blog.exception.ResourceNotFoundException;
import com.bulkadishs.blog.model.Role;
import com.bulkadishs.blog.model.User;
import com.bulkadishs.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepo userRepo;

    @Autowired
    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot login! User with this name doesn't exists"));

        List<SimpleGrantedAuthority> roles = user.getRoles().stream()
                .map(Role::toAuthority)
                .toList();

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(roles)
                .build();
    }
}
