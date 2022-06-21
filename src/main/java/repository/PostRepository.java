package repository;

import model.Post;

import java.util.*;
import java.util.stream.Collectors;

public class PostRepository {
    private final Map<Long, Post> posts = new HashMap<>();
    private long idCounter = 1;

    public List<Post> all() {
        return new ArrayList<>(posts.values())
                .stream()
                .filter(p -> !p.isRemoved())
                .collect(Collectors.toList());
    }

    public Optional<Post> getById(long id) {
        Post post = posts.get(id);
        if (post != null && !post.isRemoved()) {
            return Optional.of(post);
        }
        return Optional.empty();
    }

    public Post add(Post post) {
        post.setId(idCounter);
        posts.put(idCounter, new Post(idCounter, post.getContent()));
        idCounter++;
        return post;
    }

    public Optional<Post> save(Post post) {
        Post changedPost = posts.get(post.getId());
        if (changedPost != null && !changedPost.isRemoved()) {
            changedPost.setContent(post.getContent());
            return Optional.of(changedPost);
        }
        return Optional.empty();
    }

    public Optional<Post> removeById(long id) {
        Post removingPost = posts.get(id);
        if (removingPost != null) {
            removingPost.setRemoved(true);
            return Optional.of(removingPost);
        }
        return Optional.empty();
    }
}