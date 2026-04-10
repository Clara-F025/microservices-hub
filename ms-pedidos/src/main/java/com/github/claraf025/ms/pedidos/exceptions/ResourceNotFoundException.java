package com.github.claraf025.ms.pedidos.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
