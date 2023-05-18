package com.example.telrostesttask;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.example.telrostesttask")
@EntityScan("com.example.telrostesttask")
public class JpaConfig {
}
