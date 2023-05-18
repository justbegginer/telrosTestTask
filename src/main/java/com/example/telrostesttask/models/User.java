package com.example.telrostesttask.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usercredentials")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String patronymic;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String bornDate;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephoneNumber;

    private String photoName;
}
