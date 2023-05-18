package com.example.telrostesttask.dao;

import com.example.telrostesttask.models.ContactInfo;
import com.example.telrostesttask.models.DetailInfo;
import com.example.telrostesttask.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;

    public UserServiceImpl(@Autowired UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Override
    public List<User> findAllByContacts(ContactInfo contactInfo) {
        return userRepo.findAllByTelephoneNumberAndEmail(contactInfo.getTelephoneNumber(), contactInfo.getEmail());
    }

    @Override
    public List<User> findAllByDetails(DetailInfo detailInfo) {
        return userRepo.findAllByNameAndPatronymicAndSurnameAndBornDate(
                detailInfo.getName(), detailInfo.getPatronymic(), detailInfo.getSurname(), detailInfo.getBornDate());
    }

}
