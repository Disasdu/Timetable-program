package com.example.adilbekmailanov.myapplication.Model;


import android.support.annotation.NonNull;



public class LessonItemModel implements Comparable<LessonItemModel>{
    private int id;
    private String name;
    private String room;
    private String sTime;
    private String fTime;
    private int date;
    private boolean isHeader = false;

    public LessonItemModel(int id, String name, String sTime, String fTime, String room, int date) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.sTime = sTime;
        this.fTime = fTime;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setToSectionHeader() {
        isHeader = true;
    }

    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

    public String getSTime() {
        return sTime;
    }

    public String getFTime() {
        return fTime;
    }

    public int getDate() {
        return date;
    }

    public boolean isSectionHeader() {
        return isHeader;
    }

    @Override
    public int compareTo(@NonNull LessonItemModel lessonItemModel) {
        if (this.date > lessonItemModel.date) return 1;
        if (this.date < lessonItemModel.date) return -1;
        return sTime.compareTo(lessonItemModel.sTime);
    }
}
