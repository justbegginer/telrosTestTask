package com.example.telrostesttask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    enum Role {
        ADMIN,
        OBSERVER
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // observer have access to read requests
        // admin have access to all requests
        http.csrf().disable()
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/users/**","/usersContacts/**", "/usersDetails/**","/photos/**")
                .hasRole(Role.OBSERVER.name())
                .requestMatchers(HttpMethod.DELETE, "/users/**","/usersContacts/**", "/usersDetails/**","/photos/**")
                .hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.PATCH, "/users/**","/usersContacts/**", "/usersDetails/**")
                .hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.POST, "/users/**","/photos/**")
                .hasRole(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles(Role.ADMIN.name(), Role.OBSERVER.name())
                .build());
        manager.createUser(User.builder()
                .username("observer")
                .password(passwordEncoder().encode("observer"))
                .roles(Role.OBSERVER.name())
                .build());
        return manager;
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}