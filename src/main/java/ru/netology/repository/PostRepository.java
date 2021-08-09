package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// Stub
public class PostRepository {

    private ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();

    private long maxId = 1;

    public List<Post> all() {
        return List.copyOf(posts.values());
    }

    public Optional<Post> getById(long id) {
        Post post = posts.get(id);
        return Optional.ofNullable(post);
    }

    public Optional<Post> update(Post post) {
        Post previousPost = posts.replace(post.getId(), post);
        return previousPost == null ? Optional.empty() : Optional.of(post);
    }

    public Post add(Post post) {
        post.setId(maxId);
        posts.put(maxId, post);
        ++maxId;
        return post;
    }

    public Optional<Post> removeById(long id) {
        Post post = posts.remove(id);
        return Optional.ofNullable(post);
    }

    public long getMaxId() {
        return maxId;
    }
}
