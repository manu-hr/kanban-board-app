package com.niit.authentication.services;

import com.niit.authentication.exception.UserNotFoundException;
import com.niit.authentication.model.User;

public interface UserService {

  User saveUser(User user);
  User editUser(User user) throws UserNotFoundException;
  User findByEmailAndPassword(String email , String password) throws UserNotFoundException;
  Boolean deleteUser(String id) throws UserNotFoundException;

//  boolean validateUser(String username, String password) throws UserNotFoundException;

}
