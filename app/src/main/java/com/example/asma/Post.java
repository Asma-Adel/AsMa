package com.example.asma;

public class Post {

    private  String username;
    private  int user_profile , post_image ;
    private  boolean love , rate ;

    public Post(String username, int user_profile, int post_image, boolean love, boolean rate) {
        this.username = username;
        this.user_profile = user_profile;
        this.post_image = post_image;
        this.love = love;
        this.rate = rate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUser_profile() {
        return user_profile;
    }

    public void setUser_profile(int user_profile) {
        this.user_profile = user_profile;
    }

    public int getPost_image() {
        return post_image;
    }

    public void setPost_image(int post_image) {
        this.post_image = post_image;
    }

    public boolean isLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }

    public boolean isRate() {
        return rate;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }
}
