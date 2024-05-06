package edu.rit.croatia.swen383.g1.dm.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class WeightEntry extends LogEntry {
    private double weight;

    public WeightEntry(LocalDate date, double weight) {
        setDate(date);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toCSVString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
        String formattedDate = getDate().format(formatter);
        return String.format(Locale.US, "%s,w,%.2f", formattedDate, weight);
    }
}
