package org.example.backend.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String msg){
        super(msg);
    }
    public UserNotFoundException(){
        super("user not found");
    }
}
