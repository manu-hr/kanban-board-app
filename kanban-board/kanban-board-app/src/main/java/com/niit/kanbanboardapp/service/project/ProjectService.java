package com.niit.kanbanboardapp.service.project;

import com.niit.kanbanboardapp.domain.Column;
import com.niit.kanbanboardapp.domain.Project;
import com.niit.kanbanboardapp.domain.Task;
import com.niit.kanbanboardapp.exception.DocumentAlreadyExistException;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    Project addProject(Project project, String userId) throws DocumentAlreadyExistException;
    Project getProjectById(String id) throws DocumentNotFoundException;
    Project editProject(Project project) throws DocumentNotFoundException;
    Project deleteProject(String id) throws DocumentNotFoundException;
    List<Project> getProjectByUser(String userId) throws DocumentNotFoundException;

    Map<String, ?> editColumn(String projectId, Column column) throws DocumentNotFoundException;

    Map<String, ?> editColumn(String projectId, List<Column> column) throws DocumentNotFoundException;

    Map<String, ?> addColumn(String projectId, Column column) throws DocumentNotFoundException;
    Map<String, ?> deleteColumn(String projectId, String columnId) throws DocumentNotFoundException;
    Map<String, ?> addTask(String projectID, String columnId, Task task) throws DocumentNotFoundException;
    Map<String, ?> getTask(String projectId, String columnId) throws DocumentNotFoundException;

    Map<String, ?> assignMembersToProject(String emailId,String assignedUserId, String projectId) throws DocumentNotFoundException;


}
