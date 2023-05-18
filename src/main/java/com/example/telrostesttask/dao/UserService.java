package com.example.telrostesttask.dao;

import com.example.telrostesttask.models.ContactInfo;
import com.example.telrostesttask.models.DetailInfo;
import com.example.telrostesttask.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    List<User> findAll();

    void save(User user);

    void delete(User user);

    List<User> findAllByContacts(ContactInfo contactInfo);

    List<User> findAllByDetails(DetailInfo detailInfo);
}
