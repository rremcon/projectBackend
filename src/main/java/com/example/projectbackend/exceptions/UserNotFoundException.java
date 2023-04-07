package com.example.projectbackend.exceptions;

public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public UserNotFoundException()
        {super("User not found");}

    public UserNotFoundException(String username) {
        super("Cannot find user " + username);
    }

}
