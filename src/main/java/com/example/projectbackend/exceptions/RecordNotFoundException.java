package com.example.projectbackend.exceptions;

public class RecordNotFoundException extends RuntimeException {

    //private static final long serialVersionUID = 1L;
    public RecordNotFoundException() {
        super("Record Not Found");
    }
    public RecordNotFoundException(String message) {
        super(message);
    }

}
