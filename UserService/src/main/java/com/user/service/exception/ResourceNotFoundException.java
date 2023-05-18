package com.user.service.exception;

public class ResourceNotFoundException extends RuntimeException{

    // Extra properties that you want to manage you canwrite
    public ResourceNotFoundException(){
        super("Resource not found on server");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
