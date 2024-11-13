package com.example.notedify_recyclerview;

import java.util.Date;

public class Notes {

    private String title;
    private String content;
    private String category;
    private String date;

    public Notes(String title, String content, String category, String date) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.date = date;
    }

    // Getters
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getCategory() { return category; }
    public String getDate() { return date; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setCategory(String category) { this.category = category; }
    public void setDate(String date) { this.date = date; }
}