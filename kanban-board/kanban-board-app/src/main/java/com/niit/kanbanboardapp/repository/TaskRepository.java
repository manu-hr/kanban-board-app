package com.niit.kanbanboardapp.repository;

import com.niit.kanbanboardapp.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TaskRepository extends MongoRepository<Task, String > {
    public abstract List<Task> findAllByPriority(String priority);
    public abstract List<Task> findAllByStatus(String status);
    public abstract List<Task> findAllByAssignedTo(String userId);

}
