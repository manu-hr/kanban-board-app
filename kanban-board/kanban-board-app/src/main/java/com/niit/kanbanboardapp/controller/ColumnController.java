package com.niit.kanbanboardapp.controller;

import com.niit.kanbanboardapp.domain.Column;
import com.niit.kanbanboardapp.domain.Task;
import com.niit.kanbanboardapp.exception.DocumentAlreadyExistException;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;
import com.niit.kanbanboardapp.service.column.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/column")
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @PostMapping("/add")
    public ResponseEntity<?> addColumn(@RequestBody Column column) throws DocumentAlreadyExistException {
        return new ResponseEntity<>(columnService.addColumn(column), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllColumn() {
        return new ResponseEntity<>(columnService.getAllColumn(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAllColumn(@PathVariable String id) throws DocumentNotFoundException {
        return new ResponseEntity<>(columnService.getColumnById(id), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editColumn(@RequestBody Column column) throws DocumentNotFoundException {
        return new ResponseEntity<>(columnService.editColumn(column), HttpStatus.OK);
    }

    @PatchMapping("/add-task/{columnId}")
    public ResponseEntity<?> addTaskTOColumn(@RequestBody Task task, @PathVariable String columnId) throws DocumentNotFoundException {
        return new ResponseEntity<>(columnService.addTask(columnId, task), HttpStatus.OK);
    }

    @GetMapping("get-task/{columnId}")
    public ResponseEntity<?> getTask(@PathVariable String columnId) throws DocumentNotFoundException {
        return new ResponseEntity<>(columnService.getTaskByColumn(columnId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteColumn(@PathVariable String id) throws DocumentNotFoundException {
        Map<String, Object> response = new HashMap<>();
        response.put("msg" , columnService.deleteColumn(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
