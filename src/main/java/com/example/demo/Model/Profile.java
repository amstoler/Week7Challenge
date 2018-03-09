package com.example.demo.Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;


@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String topic;
    @ManyToMany(mappedBy = "profiles")
    private Collection<AppUser> appusers;
    private String category;

    public Profile() {
        this.appusers = new HashSet<>();
    }

    public Profile(String newsName) {
        this.topic = topic;
        this.category = category;
        this.appusers = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String newsName) {
        this.topic = newsName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Collection<AppUser> getAppusers() {
        return appusers;
    }

    public void setAppusers(Collection<AppUser> appusers) {
        this.appusers = appusers;
    }
}

