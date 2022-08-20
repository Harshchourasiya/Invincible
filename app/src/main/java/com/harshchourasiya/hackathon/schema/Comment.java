package com.harshchourasiya.hackathon.schema;

public class Comment {
    private String userName, userLevel, comment;

    public Comment() {

    }
    public Comment(String userName, String userLevel, String comment) {
        this.userName = userName;
        this.userLevel = userLevel;
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
