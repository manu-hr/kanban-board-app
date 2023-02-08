package com.niit.kanbanboardapp.repository;

import com.niit.kanbanboardapp.domain.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {
    @Query(value = "{members : ?0}")
    public List<Project> findProjectByUser(String userId);
}
