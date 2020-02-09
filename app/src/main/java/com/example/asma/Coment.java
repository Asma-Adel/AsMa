package com.example.asma;

public class Coment {
    private String commentUsername,thecomment;
    private int commentProfileImg;
    private boolean love;

     Coment(String commentUsername, String thecomment, int commentProfileImg, boolean love) {
        this.commentUsername = commentUsername;
        this.thecomment = thecomment;
        this.commentProfileImg = commentProfileImg;
        this.love = love;
    }

    public String getCommentUsername() {
        return commentUsername;
    }

    public void setCommentUsername(String commentUsername) {
        this.commentUsername = commentUsername;
    }

    public String getThecomment() {
        return thecomment;
    }

    public void setThecomment(String thecomment) {
        this.thecomment = thecomment;
    }

    public int getCommentProfileImg() {
        return commentProfileImg;
    }

    public void setCommentProfileImg(int commentProfileImg) {
        this.commentProfileImg = commentProfileImg;
    }

    public boolean isLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }
}
