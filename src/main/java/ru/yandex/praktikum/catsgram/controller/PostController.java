package ru.yandex.praktikum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.praktikum.catsgram.exception.IncorrectParameterException;
import ru.yandex.praktikum.catsgram.model.Post;
import ru.yandex.praktikum.catsgram.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll(
            @RequestParam Optional<String> sort,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size
    ) {
        if (!sort.isPresent() && !page.isPresent() && !size.isPresent()) {
            return postService.findAll(10, "asc", 0);
        }
        else if (sort.isPresent() && page.isPresent() && size.isPresent()) {
            return postService.findAll(size.get(), sort.get(), page.get());
        }
        else {
            throw new IncorrectParameterException("incorrect params");
        }
    }

    @PostMapping("/post")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/posts/{postId}")
    public Optional<Post> findById(@PathVariable Integer postId) {
        return postService.findById(postId);
    }
}
