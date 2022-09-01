package repository;

import model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
    private final Map<AtomicLong, Post> posts = new ConcurrentHashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(AtomicLong id) {
        return Optional.of(posts.get(id));
    }

    public Post add(Post post) {
        post.setId(idCounter);
        posts.put(idCounter, post);
        idCounter.getAndIncrement();
        return post;
    }

    public Optional<Post> save(Post post) {
        Post changedPost = posts.get(post.getId());
        if (changedPost != null) {
            changedPost.setContent(post.getContent());
            return Optional.of(changedPost);
        }
        return Optional.empty();
    }

    public Optional<Post> removeById(AtomicLong id) {
        return Optional.of(posts.remove(id));
    }
}