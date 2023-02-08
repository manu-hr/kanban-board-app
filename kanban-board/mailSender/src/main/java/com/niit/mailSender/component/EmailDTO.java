package com.niit.mailSender.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmailDTO {

    private String receiver,messageBody,subject;
}
