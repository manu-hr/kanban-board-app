package com.niit.kanbanboardapp.service.task;

import com.niit.kanbanboardapp.domain.Task;
import com.niit.kanbanboardapp.domain.User;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;

import java.util.List;
import java.util.Map;

public interface TaskService {
    public abstract Task addTask(Task task);
    public abstract Task editTask(Task task,String projectId,String columnId) throws DocumentNotFoundException;
    public abstract List<Task> getAllTasksOfParticularUser(String userId) throws DocumentNotFoundException;
    public abstract List<Task> getTasksBasedOnPriority(String priority,String projectId,String columnId) throws DocumentNotFoundException;
    public abstract List<Task> getTasksBasedOnStatus(String status,String projectId,String columnId) throws DocumentNotFoundException;
    public abstract Task getTaskById(String taskId) throws DocumentNotFoundException;
    public abstract boolean deleteTask(String taskId,String projectId,String columnId) throws DocumentNotFoundException;

    Map<String, ?> assignTask(String currentUserId, User assignedUser, String taskId) throws DocumentNotFoundException;
}
