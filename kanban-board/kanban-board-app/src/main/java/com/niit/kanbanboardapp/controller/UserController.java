package com.niit.kanbanboardapp.controller;

import com.niit.kanbanboardapp.domain.UserSignupData;
import com.niit.kanbanboardapp.exception.DocumentAlreadyExistException;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;
import com.niit.kanbanboardapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/auth/updateProfile")
    public ResponseEntity<?> editUserProfile(@RequestBody UserSignupData userSignupData, HttpServletRequest httpServletRequest) throws DocumentNotFoundException {
        String userId= (String) httpServletRequest.getAttribute("current_id");
        System.out.println(userId);
       return new ResponseEntity<>(userService.editProfile(userSignupData,userId), HttpStatus.OK);
    }

    @GetMapping("/auth/getUserDetail")
    public ResponseEntity<?> getDetail(HttpServletRequest httpServletRequest)
    {
        String userId= (String) httpServletRequest.getAttribute("current_id");
        return new ResponseEntity<>(userService.viewProfile(userId),HttpStatus.OK);
    }

    @PostMapping("/auth/get-users")
    public ResponseEntity<?> getUsersByIds(@RequestBody List<String> userIds) {
        return new ResponseEntity<>(userService.getAllUsersByIds(userIds),HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserSignupData userSignupData) throws DocumentAlreadyExistException {
        System.out.println(userSignupData);
        return new ResponseEntity<>(userService.register(userSignupData),HttpStatus.OK);
    }

    @PostMapping("/auth/upload-pic")
    public ResponseEntity<?> uploadImage(@RequestBody MultipartFile image, HttpServletRequest request) throws IOException, DocumentNotFoundException {
        System.out.println(image.getOriginalFilename());
        String userId= (String) request.getAttribute("current_id");
        return new ResponseEntity<>(userService.addProfilePic(userId,image),HttpStatus.OK);
    }

    @DeleteMapping("/auth/deleteUser")
    public ResponseEntity<?> deleteUser(HttpServletRequest httpServletRequest) throws DocumentNotFoundException {
        String userId= (String) httpServletRequest.getAttribute("current_id");
        return new ResponseEntity<>(userService.deleteProfile(userId),HttpStatus.OK);
    }
}
