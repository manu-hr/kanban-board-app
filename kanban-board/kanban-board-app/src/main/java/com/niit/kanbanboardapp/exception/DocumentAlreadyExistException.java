package com.niit.kanbanboardapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "Document Already Exist")
public class DocumentAlreadyExistException extends Exception{
}
