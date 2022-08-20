package com.harshchourasiya.hackathon.schema;

public class User {
    private String userId, email, password, name, timezone;
    private int level;
    private boolean isComplete;

    public User() {

    }

    public User(String userId, String email, String password, String name, String timezone, int level, boolean isComplete) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.timezone = timezone;
        this.level = level;
        this.isComplete = isComplete;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
