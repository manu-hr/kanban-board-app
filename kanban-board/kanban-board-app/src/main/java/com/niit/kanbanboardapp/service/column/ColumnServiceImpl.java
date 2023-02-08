package com.niit.kanbanboardapp.service.column;

import com.niit.kanbanboardapp.domain.Column;
import com.niit.kanbanboardapp.domain.Project;
import com.niit.kanbanboardapp.domain.Task;
import com.niit.kanbanboardapp.exception.DocumentAlreadyExistException;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;
import com.niit.kanbanboardapp.repository.ColumnRepository;
import com.niit.kanbanboardapp.repository.TaskRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Column addColumn(Column column) throws DocumentAlreadyExistException {
        column.setColumnId(new ObjectId().toString());
        try {
            return columnRepository.insert(column);

        } catch (Exception e) {
            throw new DocumentAlreadyExistException();
        }
    }

    @Override
    public Column getColumnById(String id) throws DocumentNotFoundException {
        Optional<Column> column = columnRepository.findById(id);
        if(column.isPresent())
            return column.get();
        throw new DocumentNotFoundException();
    }

    @Override
    public Column editColumn(Column columnData) throws DocumentNotFoundException {
        Optional<Column> column = columnRepository.findById(columnData.getColumnId());
        if(column.isPresent()) {
            return columnRepository.save(columnData);
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public List<Column> getAllColumn() {
        return columnRepository.findAll();
    }

    @Override
    public boolean deleteColumn(String id) throws DocumentNotFoundException {
        Optional<Column> column = columnRepository.findById(id);
        if(column.isPresent()) {
            columnRepository.deleteById(id);
            return true;
        }
        throw new DocumentNotFoundException();
    }

    @Override
    public List<Task> getTaskByColumn(String columnId) throws DocumentNotFoundException {
        return null;
//        Optional<Column> column = columnRepository.findById(columnId);
//        if(column.isPresent()) {
//            List<String> columnIds = column.get().getTask();
//            return (List<Task>) taskRepository.findAllById(columnIds);
//        }
//        throw new DocumentNotFoundException();
    }

    @Override
    public Map<String, ?> addTask(String columnId, Task task) throws DocumentNotFoundException {

        if(columnRepository.findById(columnId).isPresent()) {
            System.out.println(columnRepository.findById(columnId).get());
            Query query = new Query().addCriteria(where("columnId").is(columnId));
            Update update = new Update();
            update.push("task", task);
            mongoTemplate.update(Column.class).matching(query).apply(update).upsert();
            Map<String, Object> res = new HashMap<>();
            res.put("msg","Task added");
            res.put("data",columnRepository.findById(columnId).get());
            return res;
        } else {
            throw new DocumentNotFoundException();
        }

    }
}
