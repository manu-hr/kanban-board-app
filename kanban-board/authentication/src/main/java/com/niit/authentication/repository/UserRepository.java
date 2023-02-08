package com.niit.authentication.repository;

import com.niit.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email , String password);
}
