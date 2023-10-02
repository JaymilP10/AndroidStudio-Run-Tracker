package com.example.runtracker;

import java.util.ArrayList;
import java.util.Date;

public class Note {

    public static ArrayList<Note> noteArrayList = new ArrayList<>();
    private int id;
    private int distance;
    private int duration;
    private Date date;
    private String location;
    private String weather;
    private String type;
    private int effort;

    public Note(int id, int distance, int duration, Date date, String location, String weather, String type, int effort) {
        this.id = id;
        this.distance = distance;
        this.duration = duration;
        this.date = date;
        this.location = location;
        this.weather = weather;
        this.type = type;
        this.effort = effort;
    }

    public int getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }

    public Date getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getWeather() {
        return weather;
    }

    public String getType() {
        return type;
    }

    public int getEffort() {
        return effort;
    }
}
