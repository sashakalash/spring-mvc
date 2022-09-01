package service;

import exception.NotFoundException;
import model.Post;
import repository.PostRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(AtomicLong id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        if (post.getId().intValue() == 0) {
            return repository.add(post);
        } else {
            return repository.save(post).orElseThrow(NotFoundException::new);
        }
    }

    public Post removeById(AtomicLong id) {
        return repository.removeById(id).orElseThrow(NotFoundException::new);
    }
}
