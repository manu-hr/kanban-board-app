package com.niit.kanbanboardapp.controller;

import com.niit.kanbanboardapp.domain.Task;
import com.niit.kanbanboardapp.domain.User;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;
import com.niit.kanbanboardapp.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/add-task")
    public ResponseEntity<?> createTask(@RequestBody Task task){
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.OK);
    }

    @PutMapping("/edit-task")
    public ResponseEntity<?> updateTask(@RequestBody Task task,@PathVariable String projectId,String columnId) throws DocumentNotFoundException {
        return new ResponseEntity<>(taskService.editTask(task,projectId,columnId), HttpStatus.OK);
    }

    @GetMapping("/get-tasks-by-user/{userId}")
    public ResponseEntity<?> getTasksByProject(@PathVariable String userId) throws DocumentNotFoundException {
        return new ResponseEntity<>(taskService.getAllTasksOfParticularUser(userId),HttpStatus.OK);
    }

    @GetMapping("/get-tasks-by-priority/{priority}")
    public ResponseEntity<?> getTaskByPriority(@PathVariable String priority,@PathVariable String projectId,String columnId) throws DocumentNotFoundException {
        return new ResponseEntity<>(taskService.getTasksBasedOnPriority(priority,projectId,columnId),HttpStatus.OK);
    }

    @GetMapping("/get-tasks-by-status/{status}")
    public ResponseEntity<?> getTaskByStatus(@PathVariable String status,@PathVariable String projectId,String columnId) throws DocumentNotFoundException {
        return new ResponseEntity<>(taskService.getTasksBasedOnStatus(status,projectId,columnId),HttpStatus.OK);
    }

    @GetMapping("/get-task-by-id/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable String taskId,@PathVariable String projectId,String columnId) throws DocumentNotFoundException {
        return new ResponseEntity<>(taskService.getTaskById(taskId),HttpStatus.OK);
    }

    @DeleteMapping("/delete-task/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String taskId,@PathVariable String projectId,String columnId) throws DocumentNotFoundException {
        return new ResponseEntity<>(taskService.deleteTask(taskId,projectId,columnId),HttpStatus.OK);
    }

    @PatchMapping("/assign-task/{taskId}")
    public ResponseEntity<?> assignTask(@PathVariable String taskId, @RequestBody User user, HttpServletRequest httpServletRequest) throws DocumentNotFoundException {
        String currentUserId =(String) httpServletRequest.getAttribute("current_id");
        return new ResponseEntity<>(taskService.assignTask(currentUserId,user,taskId),HttpStatus.OK);
    }
}
