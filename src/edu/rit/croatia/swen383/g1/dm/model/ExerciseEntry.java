package edu.rit.croatia.swen383.g1.dm.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ExerciseEntry extends LogEntry {

    private Exercise exercise;
    private double time;

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exerciseName) {
        this.exercise = exerciseName;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public ExerciseEntry (LocalDate date, Exercise exerciseName, double time){
        this.exercise = exerciseName;
        this.time = time;
        setDate(date);
    }

    @Override
    public String toCSVString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
        String formattedDate = getDate().format(formatter);
        return String.format(Locale.US, "%s,e,%s,%.2f", formattedDate, exercise.getName(), time);
    }

}