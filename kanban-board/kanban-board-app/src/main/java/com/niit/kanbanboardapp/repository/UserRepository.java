package com.niit.kanbanboardapp.repository;

import com.niit.kanbanboardapp.domain.Task;
import com.niit.kanbanboardapp.domain.User;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    public abstract User findByEmail(String email);
}
