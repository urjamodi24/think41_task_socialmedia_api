package com.example.think41.model;




import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Post {

    @Id
    private String postStrId;

    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Like> likes;

    public Post() {
    }

    public Post(String postStrId, String content, LocalDateTime createdAt, Set<Like> likes) {
        this.postStrId = postStrId;
        this.content = content;
        this.createdAt = createdAt;
        this.likes = likes;
    }

    public String getPostStrId() {
        return postStrId;
    }

    public void setPostStrId(String postStrId) {
        this.postStrId = postStrId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }
}
