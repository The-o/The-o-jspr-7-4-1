package ru.netology.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.netology.model.Post;
import ru.netology.service.PostService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> all() throws IOException {
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) throws IOException {
        return service.getById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post) throws IOException {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public boolean removeById(@PathVariable long id) throws IOException {
        service.removeById(id);
        return true;
    }
}
