package com.bookofviledarkness.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(columnDefinition = "text")
    String postContent;
    Date date;

    @ManyToOne
    @JoinColumn(name = "appUser_id")
    SiteUser siteUser;

    Post() {
        //empty
    }

    public Post(String postContent, Date date) {
        this.postContent = postContent;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SiteUser getAppUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }
}
