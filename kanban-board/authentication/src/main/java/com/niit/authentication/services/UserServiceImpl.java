package com.niit.authentication.services;

import com.niit.authentication.exception.UserNotFoundException;
import com.niit.authentication.model.User;
import com.niit.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editUser(User user) throws UserNotFoundException {
        Optional<User> resp = userRepository.findById(user.getUserId());
        if(resp.isPresent()) {
            String password = resp.get().getPassword();
            user.setPassword(password);
            return userRepository.save(user);
        }
        throw new UserNotFoundException();
    }

    @Override
    public User findByEmailAndPassword(String email , String password) throws UserNotFoundException {
        User user =  userRepository.findByEmailAndPassword(email , password);
        if(user == null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public Boolean deleteUser(String id) throws UserNotFoundException {
        Optional<User> resp = userRepository.findById(id);
        if(resp.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        throw new UserNotFoundException();
    }

}
