package ru.yandex.praktikum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.praktikum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private List<Post> posts = new ArrayList<>();

    @GetMapping("/posts")
    public List<Post>findAll() {
        log.debug("Текущее количество постов: {}", posts.size());
        return posts;
    }

    @PostMapping("/post")
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }
}
