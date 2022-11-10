package ru.yandex.praktikum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.praktikum.catsgram.exception.IncorrectParameterException;
import ru.yandex.praktikum.catsgram.exception.PostNotFoundException;
import ru.yandex.praktikum.catsgram.model.Post;
import ru.yandex.praktikum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        if (page.isPresent() && page.get() < 0) {
            throw new IncorrectParameterException("page, field less then zero");
        }
        if (size.isPresent() && size.get() <= 0) {
            throw new IncorrectParameterException("size, field is equal or less then zero");
        }
        if (sort.isPresent() && !(sort.get().equals("asc") || sort.get().equals("desc"))) {
            throw new IncorrectParameterException("sort, field must be 'asc' or 'desc'");
        }

        if (!sort.isPresent() && !page.isPresent() && !size.isPresent()) {
            return postService.findAll(10, "asc", 0);
        }
        else if (sort.isPresent() && page.isPresent() && size.isPresent()){
            return postService.findAll(size.get(), sort.get(), page.get());
        }
        else {
            HashMap<String, Boolean> check = new HashMap<>();
            check.put("sort", sort.isPresent());
            check.put("page", page.isPresent());
            check.put("size", size.isPresent());
            ArrayList<String> result = new ArrayList<>();
            StringBuilder resultString = new StringBuilder();
            check.entrySet().stream().filter(x -> !x.getValue()).forEach(x -> result.add(x.getKey()));
            throw new IncorrectParameterException(result.stream().collect(Collectors.joining(", ")));
        }
    }

    @PostMapping("/post")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/posts/{postId}")
    public Optional<Post> findById(@PathVariable Integer postId) {
        if (!postService.findById(postId).isPresent()) {
            throw new PostNotFoundException("post not found");
        }
        return postService.findById(postId);
    }
}
