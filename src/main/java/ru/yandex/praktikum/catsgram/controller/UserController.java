package ru.yandex.praktikum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.praktikum.catsgram.exception.UserNotFoundException;
import ru.yandex.praktikum.catsgram.model.User;
import ru.yandex.praktikum.catsgram.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/users/{username}")
    public Optional<User> findById(@PathVariable String username) {
        log.debug("Поиск пользователя с email: {}", username);
        if (!userService.findByUsername(username).isPresent()) {
            throw new UserNotFoundException("user not found");
        }
        return userService.findByUsername(username);
    }
}
