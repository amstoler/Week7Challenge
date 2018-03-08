package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootObject {
    private String status;

    public RootObject() {
    }

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }

    private int totalResults;

    public int getTotalResults() { return this.totalResults; }

    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }

    private ArrayList<Article> articles;

    public ArrayList<Article> getArticles() { return this.articles; }

    public void setArticles(ArrayList<Article> articles) { this.articles = articles; }
}
