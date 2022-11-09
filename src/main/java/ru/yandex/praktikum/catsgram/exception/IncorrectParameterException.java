package ru.yandex.praktikum.catsgram.exception;

public class IncorrectParameterException extends RuntimeException{
    public IncorrectParameterException(String message) {
        super(message);
    }
}
