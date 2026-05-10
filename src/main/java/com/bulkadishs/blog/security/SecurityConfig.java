package com.bulkadishs.blog.security;

import com.bulkadishs.blog.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private CustomBasicAuthEntryPoint customBasicAuthEntryPoint;
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    public SecurityConfig(CustomBasicAuthEntryPoint customBasicAuthEntryPoint, CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.customBasicAuthEntryPoint = customBasicAuthEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(this.customBasicAuthEntryPoint))
                .exceptionHandling(ex ->
                        ex.accessDeniedHandler(this.customAccessDeniedHandler)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,  "/users/**", "/posts/**", "/comments/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/comments/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/posts/**").hasRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/posts/**", "/comments/**").hasRole(Role.ADMIN.name())
                )

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
