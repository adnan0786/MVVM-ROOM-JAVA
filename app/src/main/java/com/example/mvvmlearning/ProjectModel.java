package com.example.mvvmlearning;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "project")
public class ProjectModel {

    @PrimaryKey(autoGenerate = true)
    public int pId;

    @ColumnInfo(name = "p_title")
    public String title;
    public String language;
    public int watcher;
    public int issues;

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getWatcher() {
        return watcher;
    }

    public void setWatcher(int watcher) {
        this.watcher = watcher;
    }

    public int getIssues() {
        return issues;
    }

    public void setIssues(int issues) {
        this.issues = issues;
    }
}
