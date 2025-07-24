package com.example.think41.service;



import com.example.think41.model.Like;
import com.example.think41.model.Post;
import com.example.think41.repository.LikeRepository;
import com.example.think41.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public String likePost(String postStrId, String userId) {
        Optional<Post> postOpt = postRepository.findById(postStrId);
        if (postOpt.isEmpty()) return "Post not found";

        Post post = postOpt.get();
        if (likeRepository.existsByPostAndUserId(post, userId))
            return "already liked";

        Like like = new Like();
        like.setUserId(userId);
        like.setPost(post);
        likeRepository.save(like);
        return "liked";
    }

    public String unlikePost(String postStrId, String userId) {
        Optional<Post> postOpt = postRepository.findById(postStrId);
        if (postOpt.isEmpty()) return "Post not found";

        Post post = postOpt.get();
        Optional<Like> likeOpt = likeRepository.findByPostAndUserId(post, userId);
        if (likeOpt.isEmpty()) return "not liked previously";

        likeRepository.delete(likeOpt.get());
        return "unliked";
    }

    public long getLikeCount(String postStrId) {
        Post post = postRepository.findById(postStrId).orElseThrow();
        return likeRepository.countByPost(post);
    }

    public List<Map<String, Object>> getTopLikedPosts(int limit) {
        List<Post> posts = postRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        posts.sort((p1, p2) ->
                Long.compare(likeRepository.countByPost(p2), likeRepository.countByPost(p1))
        );

        for (int i = 0; i < Math.min(limit, posts.size()); i++) {
            Post p = posts.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("post_str_id", p.getPostStrId());
            map.put("like_count", likeRepository.countByPost(p));
            result.add(map);
        }

        return result;
    }

    public List<String> getLikedPostsByUser(String userId) {
        List<Like> likes = likeRepository.findByUserId(userId);
        List<String> postIds = new ArrayList<>();
        for (Like like : likes) {
            postIds.add(like.getPost().getPostStrId());
        }
        return postIds;
    }
}
