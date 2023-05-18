package com.example.telrostesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class TelrosTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelrosTestTaskApplication.class, args);
    }

}
