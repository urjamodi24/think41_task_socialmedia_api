package com.example.think41.repository;



import com.example.think41.model.Like;
import com.example.think41.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPostAndUserId(Post post, String userId);
    Optional<Like> findByPostAndUserId(Post post, String userId);
    long countByPost(Post post);
    List<Like> findByUserId(String userId);
}
