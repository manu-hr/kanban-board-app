package com.niit.kanbanboardapp.service.project;

import com.mongodb.BasicDBObject;
import com.niit.kanbanboardapp.domain.*;
import com.niit.kanbanboardapp.exception.DocumentAlreadyExistException;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;
import com.niit.kanbanboardapp.rabbitmq.MailProducer;
import com.niit.kanbanboardapp.repository.ProjectRepository;
import com.niit.kanbanboardapp.repository.TaskRepository;
import com.niit.kanbanboardapp.repository.UserRepository;
import com.niit.kanbanboardapp.service.column.ColumnService;
import com.niit.kanbanboardapp.service.user.UserService;
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
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailProducer mailProducer;

    @Override
    public Project addProject(Project project, String userId) throws DocumentAlreadyExistException {

        project.setProjectId(new ObjectId().toString());
        List<Column> columns = new ArrayList<>();
        columns.add(new Column(new ObjectId().toString(),"TO-DO",3,new ArrayList<>()));
        columns.add(new Column(new ObjectId().toString(),"In-Progress",3,new ArrayList<>()));
        columns.add(new Column(new ObjectId().toString(),"Review",3,new ArrayList<>()));
        columns.add(new Column(new ObjectId().toString(),"Done",3,new ArrayList<>()));

        List<String> members = new ArrayList<>(Collections.singletonList(userId));

        project.setMembers(members);
        project.setColumns(columns);
        try {
            return projectRepository.insert(project);

        }catch (Exception e) {
            throw new DocumentAlreadyExistException();
        }
    }

    @Override
    public Project getProjectById(String id) throws DocumentNotFoundException {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isPresent())
            return project.get();
        throw new DocumentNotFoundException();
    }

    @Override
    public Project editProject(Project projectData) throws DocumentNotFoundException {
        Optional<Project> project = projectRepository.findById(projectData.getProjectId());
        if(project.isPresent()) {
            Query query = new Query().addCriteria(where("projectId").is(projectData.getProjectId()));
            Update update = new Update();
            if(projectData.getProjectName() != null ) {
                update.set("projectName", projectData.getProjectName());
            }
            if(projectData.getProjectDescription() != null ) {
                update.set("projectDescription",projectData.getProjectDescription());
            }
            if(projectData.getDeadline() != null) {
                update.set("deadline",projectData.getDeadline());
            }
            mongoTemplate.update(Project.class).matching(query).apply(update).upsert();
            return projectRepository.findById(projectData.getProjectId()).get();
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public Project deleteProject(String id) throws DocumentNotFoundException {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isPresent()) {
            projectRepository.deleteById(id);
            Optional<Project> updatedProject = projectRepository.findById(id);
            if(updatedProject.isPresent())
                return projectRepository.findById(id).get();
            return null;
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public List<Project> getProjectByUser(String userId) throws DocumentNotFoundException {
        System.out.println(userId);
        List<Project> projects =  projectRepository.findProjectByUser(userId);
        if(projects.size() == 0)
            throw new DocumentNotFoundException();
        return projects;
    }

    @Override
    public Map<String, ?> editColumn(String projectId, Column column) throws DocumentNotFoundException {
        System.out.println(column);
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.isPresent()) {
            Query query = new Query().addCriteria(where("projectId").is(projectId).and("columns.columnId").is(column.getColumnId()));
            Update update = new Update();
            if(column.getColumnTitle() != null) {
                update.set("columns.$.columnTitle",column.getColumnTitle());
            }
            if(column.getTaskLimit() > 0  ) {
                update.set("columns.$.taskLimit",column.getTaskLimit());
            }
            mongoTemplate.update(Project.class).matching(query).apply(update).upsert();
            Map<String, Object> res = new HashMap<>();
            res.put("msg","Task added");
            res.put("data",projectRepository.findById(projectId).get());
            return res;
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public Map<String, ?> editColumn(String projectId, List<Column> column) throws DocumentNotFoundException {
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.isPresent()) {
            Query query = new Query().addCriteria(where("projectId").is(projectId));
            Update update = new Update();
            update.set("columns",column);
            mongoTemplate.update(Project.class).matching(query).apply(update).upsert();
            Map<String, Object> res = new HashMap<>();
            res.put("msg","Task added");
            res.put("data",projectRepository.findById(projectId).get());
            return res;
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public Map<String, ?> addColumn(String projectId, Column column) throws DocumentNotFoundException {
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.isPresent()) {
            Query query = new Query().addCriteria(where("projectId").is(projectId));
            Update update = new Update();
            column.setColumnId(new ObjectId().toString());
            column.setTask(new ArrayList<Task>());
            update.push("columns",column);
            mongoTemplate.update(Project.class).matching(query).apply(update).upsert();
            Map<String, Object> res = new HashMap<>();
            res.put("msg","Column added");
            res.put("data",projectRepository.findById(projectId).get());
            return res;
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public Map<String, ?> deleteColumn(String projectId, String columnId) throws DocumentNotFoundException {
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.isPresent()) {
            Query query = new Query().addCriteria(where("projectId").is(projectId));
            Update update = new Update();
            update.pull("columns", new BasicDBObject("columnId",columnId));
            mongoTemplate.update(Project.class).matching(query).apply(update).upsert();
            Map<String, Object> res = new HashMap<>();
            res.put("msg","Column Deleted");
            res.put("data",projectRepository.findById(projectId).get());
            return res;
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public Map<String, ?> addTask(String projectId, String columnId, Task task) throws DocumentNotFoundException {
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.isPresent()) {
            Query query = new Query().addCriteria(where("projectId").is(projectId).and("columns.columnId").is(columnId));
            Update update = new Update();
            task.setTaskId(new ObjectId().toString());
            update.push("columns.$.task",task);
            mongoTemplate.update(Project.class).matching(query).apply(update).upsert();
            Map<String, Object> res = new HashMap<>();
            res.put("msg","Task added");
            res.put("data",projectRepository.findById(projectId).get());
            return res;
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public Map<String, ?> getTask(String projectId, String columnId) throws DocumentNotFoundException {
        return null;
    }

    @Override
    public Map<String, ?> assignMembersToProject(String emailId,String assignedUserId, String projectId) throws DocumentNotFoundException {
        User user=userRepository.findByEmail(emailId);
        if(user!=null){
            Query query = new Query().addCriteria(where("projectId").is(projectId));
            Update update = new Update();
            update.addToSet("members",user.getUserId());
            mongoTemplate.update(Project.class).matching(query).apply(update).upsert();
            User assignedUser = userRepository.findById(assignedUserId).get();
            Project tempProject = projectRepository.findById(projectId).get();
            String msgBody = assignedUser.getUserName() + " has added you to the project "+ tempProject.getProjectName();
            EmailDTO emailDTO=new EmailDTO(emailId,msgBody,"New Project Assigned");
            mailProducer.sendMailDtoToQueue(emailDTO);
            Map<String, Object> res = new HashMap<>();
            res.put("msg","Member added");
            res.put("data",tempProject);
            return res;
        }
        throw new DocumentNotFoundException();
    }

}
