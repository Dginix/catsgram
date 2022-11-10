package ru.yandex.praktikum.catsgram.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class User {
    private Integer id;
    private String username;
    private String nickname;
}
