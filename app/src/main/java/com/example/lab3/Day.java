package com.example.lab3;

public class Day {
    private String day;
    private double startTime;
    private double endTime;

    public Day(String day, double startTime, double endTime){
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDay(){
        return this.day;
    }

    public double getStartTime(){
        return this.startTime;
    }

    public double getEndTime(){
        return this.endTime;
    }
}
