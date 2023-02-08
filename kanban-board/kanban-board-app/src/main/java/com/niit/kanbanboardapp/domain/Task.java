package com.niit.kanbanboardapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private String taskId;
    private String taskName,taskDescription,priority;
    private Date assignedDateAndTime,updatedDateAndTime,deadlineDateAndTime;
    private String status,assignee;
    private List<String> assignedTo;
    private List<String> watcher;

}
