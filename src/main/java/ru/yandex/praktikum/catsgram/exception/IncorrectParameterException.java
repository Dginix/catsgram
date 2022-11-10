package ru.yandex.praktikum.catsgram.exception;

public class IncorrectParameterException extends RuntimeException{
    private final String parameter;

    public IncorrectParameterException(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}