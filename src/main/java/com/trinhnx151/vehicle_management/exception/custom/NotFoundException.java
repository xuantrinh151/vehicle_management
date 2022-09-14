package com.trinhnx151.vehicle_management.exception.custom;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super();
    }
    public NotFoundException(String message) {
        super(message);
    }
}
