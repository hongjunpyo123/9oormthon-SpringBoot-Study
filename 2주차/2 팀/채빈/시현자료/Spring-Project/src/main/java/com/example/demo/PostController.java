package com.example.demo;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private List<Post> posts = new ArrayList<>();
    private Long currentId = 1L;

    // CREATE
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        post.setId(currentId++);
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    // READ ALL
    @GetMapping
    public List<Post> getAllPosts() {
        return posts;
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @RequestBody Post updatedPost
    ) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(p -> {
                    p.setTitle(updatedPost.getTitle());
                    p.setContent(updatedPost.getContent());
                    return ResponseEntity.ok(p);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // PARTIAL UPDATE (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Post> patchPost(
            @PathVariable Long id,
            @RequestBody Map<String, String> updates
    ) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(p -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "title": p.setTitle(value); break;
                            case "content": p.setContent(value); break;
                        }
                    });
                    return ResponseEntity.ok(p);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        boolean removed = posts.removeIf(p -> p.getId().equals(id));
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
