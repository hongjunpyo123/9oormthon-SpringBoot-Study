package com.example.demo;

public class Post {
    private Long id;
    private String title;
    private String content;

    // 생성자, getter, setter
    public Post() {}

    public Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getter/Setter 수동 생성
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}