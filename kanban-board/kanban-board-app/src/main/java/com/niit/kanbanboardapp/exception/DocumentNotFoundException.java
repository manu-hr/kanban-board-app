package com.niit.kanbanboardapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "No Document Found")
public class DocumentNotFoundException extends Exception{
}
