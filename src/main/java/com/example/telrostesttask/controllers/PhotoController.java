package com.example.telrostesttask.controllers;

import com.example.telrostesttask.models.Status;
import com.example.telrostesttask.dao.UserServiceImpl;
import com.example.telrostesttask.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/photos")
public class PhotoController {
    private final UserServiceImpl userService;

    public PhotoController(@Autowired UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/getPhoto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable long id) throws IOException {
        File reader = new File(userService.findById(id).get().getPhotoName());
        return Files.readAllBytes(reader.toPath());
    }

    @PostMapping(value = "/putPhoto/{id}") // add OR change user's photo
    public Status putPhoto(@PathVariable("id") long id, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (userService.findById(id).isEmpty()) {
            return new Status(false,"There is no user with this id");
        }

        User user = userService.findById(id).get();
        String filename = user.getId() + "_" + multipartFile.getOriginalFilename();
        String path = "src/main/resources/photos/";
        //write file on server
        File file = new File(path + filename);
        if(user.getPhotoName() == null) { // in case of adding photo
            // if file with current name already exist, when try to indexed it and find free name
            for (int i = 0; file.exists(); i++) {
                file = new File(path + i + "_" + filename);
            }
        }
        else{ // in case of changing photo
            file = new File(user.getPhotoName());
        }
        multipartFile.transferTo(file.toPath());
        //add photo path to current user
        user.setPhotoName(file.toPath().toString());
        userService.save(user);

        return new Status(true, "photo upload success");
    }

    @DeleteMapping("/deletePhoto/{id}") // delete photo from user
    public Status deleteUserPhoto(@PathVariable("id") long id) {
        if (userService.findById(id).isEmpty()) {
            return new Status(false, "There is no user with this id");
        }
        if (userService.findById(id).get().getPhotoName() == null) {
            return new Status(false, "No photo of this user");
        }
        User user = userService.findById(id).get();
        //delete photo from server
        if (!new File(user.getPhotoName()).delete()) {
            return new Status(false, "File cannot be deleted.Reason is unknown.");
        }
        // delete photo path from current user
        user.setPhotoName(null);
        userService.save(user);
        return new Status(true, "Photo deleted successfully");
    }
}
