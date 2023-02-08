package com.niit.kanbanboardapp.controller;

import com.niit.kanbanboardapp.domain.Column;
import com.niit.kanbanboardapp.domain.Project;
import com.niit.kanbanboardapp.domain.Task;
import com.niit.kanbanboardapp.exception.DocumentAlreadyExistException;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;
import com.niit.kanbanboardapp.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/add")
    public ResponseEntity<?> addProjects(@RequestBody Project project, HttpServletRequest request) throws DocumentAlreadyExistException {
        String userId = (String) request.getAttribute("current_id");
        return  new ResponseEntity<>(projectService.addProject(project, userId), HttpStatus.OK);
    }

    @GetMapping("/get-by-user")
    public ResponseEntity<?> getProjects(HttpServletRequest request) throws DocumentNotFoundException {
        String userId = (String) request.getAttribute("current_id");
        System.out.println(userId);
        return new ResponseEntity<>(projectService.getProjectByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProject(@PathVariable String id) throws DocumentNotFoundException {
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editProject(@RequestBody Project project) throws DocumentNotFoundException {
        return new ResponseEntity<>(projectService.editProject(project),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable String id) throws DocumentNotFoundException {
//        Map<String, Object> response = new HashMap<>();
//        response.put("msg" , projectService.deleteProject(id));
        return new ResponseEntity<>(projectService.deleteProject(id), HttpStatus.OK);
    }

    @PatchMapping("/edit-column-details/{projectId}")
    public ResponseEntity<?> editColumn(@PathVariable String projectId, @RequestBody Column column) throws  DocumentNotFoundException {
        return new ResponseEntity<>(projectService.editColumn(projectId,column),HttpStatus.OK);
    }

    @PatchMapping("/edit-column/{projectId}")
    public ResponseEntity<?> editColumn(@PathVariable String projectId, @RequestBody List<Column> column) throws  DocumentNotFoundException {
        return new ResponseEntity<>(projectService.editColumn(projectId,column),HttpStatus.OK);
    }

    @PatchMapping("/add-column/{projectId}")
    public ResponseEntity<?> addColumn(@PathVariable String projectId, @RequestBody Column column) throws  DocumentNotFoundException {
        return new ResponseEntity<>(projectService.addColumn(projectId,column),HttpStatus.OK);
    }

    @PatchMapping("/delete-column/{projectId}/{columnId}")
    public ResponseEntity<?> deleteColumn(@PathVariable String projectId,@PathVariable String columnId) throws  DocumentNotFoundException {
        return new ResponseEntity<>(projectService.deleteColumn(projectId,columnId),HttpStatus.OK);
    }

    @PatchMapping("/add-task/{projectId}/{columnId}")
    public ResponseEntity<?> addTask(@PathVariable String projectId,@PathVariable String columnId, @RequestBody Task task) throws  DocumentNotFoundException {
        return new ResponseEntity<>(projectService.addTask(projectId,columnId, task),HttpStatus.OK);
    }

    @PatchMapping("/assign-member/{email}/{projectId}")
    public ResponseEntity<?> assignMember(@PathVariable String email,@PathVariable String projectId, HttpServletRequest request) throws DocumentNotFoundException {
        String assignedUserId = (String) request.getAttribute("current_id");
        System.out.println(assignedUserId);
        return new ResponseEntity<>(projectService.assignMembersToProject(email,assignedUserId,projectId),HttpStatus.OK);
    }

}
