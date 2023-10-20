package com.example.dataanalyticshubfx;

public class Post {

    private String postId;
    private String userId;
    private String likes;
    private String shares;
    private String content;
    private String dateTime;
    private String author;

    public Post(String postId, String userId, String likes, String shares, String content, String dateTime, String author) {
        this.postId = postId;
        this.userId = userId;
        this.likes = likes;
        this.shares = shares;
        this.content = content;
        this.dateTime = dateTime;
        this.author = author;
    }

    public String getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }

    public String getLikes() {
        return likes;
    }

    public String getShares() {
        return shares;
    }

    public String getContent() {
        return content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getAuthor() {
        return author;
    }
}