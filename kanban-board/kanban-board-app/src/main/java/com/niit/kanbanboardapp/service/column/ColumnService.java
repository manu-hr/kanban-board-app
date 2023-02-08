package com.niit.kanbanboardapp.service.column;

import com.niit.kanbanboardapp.domain.Column;
import com.niit.kanbanboardapp.domain.Project;
import com.niit.kanbanboardapp.domain.Task;
import com.niit.kanbanboardapp.exception.DocumentAlreadyExistException;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;

import java.util.List;
import java.util.Map;

public interface ColumnService {
    Column addColumn(Column column) throws DocumentAlreadyExistException;
    Column getColumnById(String id) throws DocumentNotFoundException;
    Column editColumn(Column columnData) throws DocumentNotFoundException;
    List<Column> getAllColumn() ;
    boolean deleteColumn(String id) throws DocumentNotFoundException;
    List<Task> getTaskByColumn(String columnId) throws DocumentNotFoundException;
    Map<String, ?>  addTask(String id, Task task) throws DocumentNotFoundException;
}
