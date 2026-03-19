//package com.bulkadishs.blog.security;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
////    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) {
//        http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/users", "/users/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/**").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//
////    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
