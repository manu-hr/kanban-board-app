package com.niit.authentication.controller;

import com.niit.authentication.exception.UserNotFoundException;
import com.niit.authentication.model.User;
import com.niit.authentication.services.SecurityTokenGenerator;
import com.niit.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/authentication")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    @PostMapping("/register")
    public ResponseEntity<?>  saveUser(@RequestBody User user) {
        try {
            System.out.println("Hi !!!");
            System.out.println(user);
            User createdUser = userService.saveUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Try after sometime!!!", HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {

        Map<String, String> map = null;
        try {
            User userObj = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (userObj.getEmail().equals(user.getEmail())) {
                map = securityTokenGenerator.generateToken(userObj);
            }
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }
        catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }
        catch (Exception e){
            return new ResponseEntity<Object>("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editUserPassword(@RequestBody User user) throws  UserNotFoundException {
        try {
            User editedUser = userService.editUser(user);
            return new ResponseEntity<>(editedUser, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            throw ex;
        }
        catch (Exception e) {
            return new ResponseEntity<Object>("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody String id) throws UserNotFoundException {
        if(userService.deleteUser(id)) {
            Map<String, String> resp = new HashMap<>();
            resp.put("msg" , "User Deleted");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        throw new UserNotFoundException();
    }
}
