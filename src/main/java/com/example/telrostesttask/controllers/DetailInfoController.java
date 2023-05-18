package com.example.telrostesttask.controllers;

import com.example.telrostesttask.models.Status;
import com.example.telrostesttask.models.DetailInfo;
import com.example.telrostesttask.dao.UserServiceImpl;
import com.example.telrostesttask.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usersDetails")
public class DetailInfoController {
    private final UserServiceImpl userService;

    public DetailInfoController(@Autowired UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public DetailInfo viewDetailInfo(@PathVariable("id") long id) { // check detail info of user with such id
        DetailInfo detailInfo = new DetailInfo();
        detailInfo.buildFromUser(userService.findById(id).get());
        return detailInfo;
    }

    @PatchMapping("/{id}")
    public Status changeDetailInfo(@PathVariable("id") long id, @RequestBody DetailInfo detailInfo) {// change detail info of user with such id
        if (userService.findById(id).isEmpty()) {
            return new Status(false, "there is no such user to update");
        }
        userService.save(detailInfo.getUserFrom(userService.findById(id).get()));
        return new Status(true, "details of user successfully update");
    }

    @DeleteMapping
    public Status deleteByDetail(@RequestBody DetailInfo detailInfo) {// delete user with such details
        List<User> users = userService.findAllByDetails(detailInfo);
        if (users.isEmpty()) {
            return new Status(false ,"there is no such user with current detail");
        }
        if (users.size() != 1) {
            return new Status(false,"there is more than one user with current detail");
        }
        userService.delete(users.get(0));
        return new Status(true, "user with current detail delete successfully");
    }

    @GetMapping
    public List<User> findByDetails(@RequestBody DetailInfo detailInfo){
        return userService.findAllByDetails(detailInfo);
    }
}
