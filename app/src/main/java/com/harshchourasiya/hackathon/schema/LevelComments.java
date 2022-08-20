package com.harshchourasiya.hackathon.schema;

import java.util.List;

public class LevelComments {

    private String levelName, levelTask;
    private List<Comment> commentList;

    public LevelComments() {}

    public LevelComments(String levelName, String levelTask, List<Comment> commentList) {
        this.levelName = levelName;
        this.levelTask = levelTask;
        this.commentList = commentList;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelTask() {
        return levelTask;
    }

    public void setLevelTask(String levelTask) {
        this.levelTask = levelTask;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
