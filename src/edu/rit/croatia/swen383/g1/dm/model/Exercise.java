package edu.rit.croatia.swen383.g1.dm.model;

import java.util.Locale;

public class Exercise {
    private String name;
    private double calories;

    public Exercise (String name, double calories){
        this.name = name;
        this.calories = calories;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getCalories(){
        return this.calories;
    }
    public void setCalories(double calories){
        this.calories = calories;
    }

    public String toCSVString() {
        return String.format(Locale.US, "e,%s,%.2f", name, calories);
    }
}