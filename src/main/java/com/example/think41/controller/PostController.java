package com.example.think41.controller;



import com.example.think41.model.Post;
import com.example.think41.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Map<String, Object> createPost(@RequestBody Post post) {
        Post saved = postService.createPost(post);
        return Map.of(
                "internal_db_id", saved.getPostStrId(),
                "status", "created"
        );
    }

    @PostMapping("/{postStrId}/like")
    public Map<String, String> likePost(@PathVariable String postStrId, @RequestBody Map<String, String> body) {
        String userId = body.get("user_id_str");
        String status = postService.likePost(postStrId, userId);
        return Map.of("status", status);
    }

    @DeleteMapping("/{postStrId}/like")
    public Map<String, String> unlikePost(@PathVariable String postStrId, @RequestBody Map<String, String> body) {
        String userId = body.get("user_id_str");
        String status = postService.unlikePost(postStrId, userId);
        return Map.of("status", status);
    }

    @GetMapping("/{postStrId}/likes")
    public Map<String, Object> getLikeCount(@PathVariable String postStrId) {
        long count = postService.getLikeCount(postStrId);
        return Map.of("post_str_id", postStrId, "like_count", count);
    }

    @GetMapping("/top")
    public List<Map<String, Object>> getTopLikedPosts(@RequestParam("limit") int limit) {
        return postService.getTopLikedPosts(limit);
    }

    @GetMapping("/users/{userId}/liked-posts")
    public List<String> getLikedPostsByUser(@PathVariable String userId) {
        return postService.getLikedPostsByUser(userId);
    }
}
