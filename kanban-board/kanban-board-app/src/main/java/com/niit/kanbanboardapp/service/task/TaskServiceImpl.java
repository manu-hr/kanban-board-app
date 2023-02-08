package com.niit.kanbanboardapp.service.task;

import com.niit.kanbanboardapp.domain.Column;
import com.niit.kanbanboardapp.domain.Project;
import com.niit.kanbanboardapp.domain.Task;
import com.niit.kanbanboardapp.domain.User;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;
import com.niit.kanbanboardapp.repository.ColumnRepository;
import com.niit.kanbanboardapp.repository.ProjectRepository;
import com.niit.kanbanboardapp.repository.TaskRepository;
import com.niit.kanbanboardapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ColumnRepository columnRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Task addTask(Task task) {
        task.setTaskId(new ObjectId().toString());
        return taskRepository.save(task) ;
    }

    @Override
    public Task editTask(Task task,String projectId,String columnId) throws DocumentNotFoundException {
        Optional<Project> project=projectRepository.findById(projectId);
        if(project.isPresent()){
            Query query = new Query().addCriteria(where("projectId").is(projectId).and("columns.columnId").is(columnId).and("tasks.taskId").is(task.getTaskId()));
            Update update = new Update();
            if(task.getTaskName() != null) {
                update.set("taskName",task.getTaskName());
            }
            if(task.getTaskDescription() != null) {
                update.set("taskDescription",task.getTaskDescription());
            }
            if(task.getPriority() != null) {
                update.set("priority",task.getPriority());
            }
            if(task.getStatus() != null) {
                update.set("status",task.getStatus());
            }
            mongoTemplate.update(Project.class).matching(query).apply(update).upsert();
            return taskRepository.findById(task.getTaskId()).get();
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public List<Task> getAllTasksOfParticularUser(String userId) throws DocumentNotFoundException {
        User user=userRepository.findById(userId).get();
        if(user!=null){
            List<Task> taskList=taskRepository.findAllByAssignedTo(userId);
            return taskList;
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public List<Task> getTasksBasedOnPriority(String priority,String projectId,String columnId) throws DocumentNotFoundException {
        Query query = new Query().addCriteria(where("projectId").is(projectId).and("columns.columnId").is(columnId));

//        update.pull()
        List<Task> taskList=taskRepository.findAllByPriority(priority);
        if(taskList.size()!=0){
            return taskList;
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public List<Task> getTasksBasedOnStatus(String status,String projectId,String columnId) throws DocumentNotFoundException {
        List<Task> taskList=taskRepository.findAllByStatus(status);
        if(taskList.size()!=0) {
            return taskList;
        }
        throw new DocumentNotFoundException();

    }

    @Override
    public Task getTaskById(String taskId) throws DocumentNotFoundException {
        Optional<Task> task=taskRepository.findById(taskId);
        if(task.isPresent())
        {
            Task task1=taskRepository.findById(taskId).get();
            return task1;
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public boolean deleteTask(String taskId,String projectId,String columnId) throws DocumentNotFoundException {
        Optional<Task> task=taskRepository.findById(taskId);
        if(task.isPresent())
        {
            taskRepository.deleteById(taskId);
            return true;
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public Map<String, ?> assignTask(String currentUserId, User assignedUser, String taskId) throws DocumentNotFoundException {
        if(taskRepository.findById(taskId).isPresent()){
            Query query = new Query().addCriteria(where("taskId").is(taskId));
            Update update = new Update();
            update.set("assignee",currentUserId);
            Set<String> assignedToUser= Collections.singleton(assignedUser.getUserId());
            update.set("assignedTo",assignedToUser);
            Set<String> watcherUsers=new HashSet<>();
            watcherUsers.add(currentUserId);
            watcherUsers.addAll(assignedToUser);
            update.set("watcher",watcherUsers);
            mongoTemplate.update(Task.class).matching(query).apply(update).upsert();
            Map<String, Object> res = new HashMap<>();
            res.put("msg","Assignee,AssignedTo,Watcher added");
            res.put("data",taskRepository.findById(taskId).get());
            return res;
        } else {
            throw new DocumentNotFoundException();
        }
    }

}
