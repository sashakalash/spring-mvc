package controller;

import model.Post;
import service.PostService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable AtomicLong id) {
        return service.getById(id);
    }

    @PostMapping
    public Post add(@RequestBody Post post) {
        return service.save(post);
    }

    @PostMapping("/{id}")
    public Post save(@RequestBody Post post) {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(AtomicLong id) {
        service.removeById(id);
    }
}