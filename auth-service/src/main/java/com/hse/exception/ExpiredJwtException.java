package com.hse.exception;

public class ExpiredJwtException extends RuntimeException{
    public ExpiredJwtException(String message) {
        super(message);
    }
}
