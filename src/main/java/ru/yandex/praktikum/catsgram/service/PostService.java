package ru.yandex.praktikum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.praktikum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostService {
    private final List<Post> posts = new ArrayList<>();

    public List<Post> findAll(Integer size, String sort, Integer from) {
        return posts.stream()
                .sorted(((o1, o2) -> {
                    if (sort.equals("desc")) {
                        return o2.getCreationDate().compareTo(o1.getCreationDate());
                    }
                    else if (sort.equals("asc")){
                        return o1.getCreationDate().compareTo(o2.getCreationDate());
                    }
                    else return 0;
                }))
                .skip(from)
                .limit(size)
                .toList();
    }

    public Post create(Post post) {
        posts.add(post);
        return post;
    }

    public Optional<Post> findById(Integer id) {
        return posts.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();
    }
}
