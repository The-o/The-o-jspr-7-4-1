package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) throws NotFoundException {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) throws NotFoundException  {
        return post.getId() == 0
            ? repository.add(post)
            : repository.update(post).orElseThrow(NotFoundException::new);
    }

    public void removeById(long id) throws NotFoundException  {
        repository.removeById(id).orElseThrow(NotFoundException::new);
    }
}
