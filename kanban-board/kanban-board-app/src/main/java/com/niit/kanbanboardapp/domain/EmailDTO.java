package com.niit.kanbanboardapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmailDTO {

    private String receiver,messageBody,subject;
}
