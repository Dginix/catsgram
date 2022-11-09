package ru.yandex.praktikum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.praktikum.catsgram.model.Post;
import ru.yandex.praktikum.catsgram.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        log.debug("Количество пользователей: {}", users.size());
        return users;
    }

    public User create(User user) {
        users.add(user);
        return user;
    }
}
