package com.harshchourasiya.hackathon.schema;

public class Level {

    private String level, levelName;
    private int levelId;

    public Level() {}

    public Level(String level, String levelName, int levelId) {
        this.level = level;
        this.levelName = levelName;
        this.levelId = levelId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }
}
