package com.example.telrostesttask.controllers;

import com.example.telrostesttask.models.Status;
import com.example.telrostesttask.models.ContactInfo;
import com.example.telrostesttask.dao.UserServiceImpl;
import com.example.telrostesttask.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usersContacts")
public class ContactInfoController {
    private final UserServiceImpl userService;

    public ContactInfoController(@Autowired UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ContactInfo viewContactInfo(@PathVariable("id") long id) { // get contact info of user with such id
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.buildFromUser(userService.findById(id).get());
        return contactInfo;
    }

    @PatchMapping("/{id}")
    public Status changeContactInfo(@PathVariable("id") long id, @RequestBody ContactInfo contactInfo) { // change contacts info of user with such id
        if (userService.findById(id).isEmpty()) {
            return new Status(false,"there is no such user");
        }
        userService.save(contactInfo.getUserFrom(userService.findById(id).get()));
        return new Status(true, "contacts successfully update");
    }

    @DeleteMapping
    public Status deleteByContact(@RequestBody ContactInfo contactInfo) {// delete user with such contacts
        List<User> users = userService.findAllByContacts(contactInfo);
        if (users.isEmpty()) {
            return new Status(false, "there is no such user with current contacts");
        }
        if (users.size() != 1) {
            return new Status(false,"there is more than one user with current contacts");
        }
        userService.delete(users.get(0));
        return new Status(true, "user with current contacts delete successfully");
    }

    @GetMapping
    public List<User> findByContact(@RequestBody ContactInfo contactInfo){
        return userService.findAllByContacts(contactInfo);
    }
}
