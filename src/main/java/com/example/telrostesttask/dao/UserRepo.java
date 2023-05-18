package com.example.telrostesttask.dao;

import com.example.telrostesttask.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findAllByTelephoneNumberAndEmail(String telephoneNumber, String email);

    List<User> findAllByNameAndPatronymicAndSurnameAndBornDate(String name, String patronymic, String surname, String bornDate);
}
