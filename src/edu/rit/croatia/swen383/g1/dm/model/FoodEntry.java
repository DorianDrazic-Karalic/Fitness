package edu.rit.croatia.swen383.g1.dm.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FoodEntry extends LogEntry {
    private Food foodConsumed;
    private double count;

    public FoodEntry(LocalDate date, Food foodConsumed, double count) {
        setDate(date);
        this.foodConsumed = foodConsumed;
        this.count = count;
    }

    public Food getFoodConsumed() {
        return foodConsumed;
    }

    public double getCount() {
        return count;
    }

    @Override
    public String toCSVString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
        String formattedDate = getDate().format(formatter);
        return String.format(Locale.US, "%s,f,%s,%.2f", formattedDate, foodConsumed.getName(), count);
    }
}
