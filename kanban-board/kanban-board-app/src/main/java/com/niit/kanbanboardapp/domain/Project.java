package com.niit.kanbanboardapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Project {
    @Id
    private String projectId;
    @Indexed(unique = true)
    private String projectName;
    private String projectDescription;
    private Date deadline,createdDate;
    private List<String> members;
    private List<Column> columns;
}
