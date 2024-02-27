package com.example.firstproject.Exception;

import lombok.Getter;

@Getter
public class Exception extends RuntimeException{
    private final ExceptionEnum ExceptionCode;

    public Exception(ExceptionEnum ExceptionCode){
        super(ExceptionCode.getMessage());
        this.ExceptionCode = ExceptionCode;
    }
}
