package repository;

import model.Post;

import java.util.*;

public class PostRepository {
    private final Map<Long, Post> posts = new HashMap<>();
    private long idCounter = 1;

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.of(posts.get(id));
    }

    public Post add(Post post) {
        post.setId(idCounter);
        posts.put(idCounter, post);
        idCounter++;
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

    public Optional<Post> removeById(long id) {
        return Optional.of(posts.remove(id));
    }
}