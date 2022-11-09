package ru.yandex.praktikum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.praktikum.catsgram.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(x -> x.getUsername().equals(username))
                .findFirst();
    }
}
