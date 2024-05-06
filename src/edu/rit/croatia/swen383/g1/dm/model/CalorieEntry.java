package edu.rit.croatia.swen383.g1.dm.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CalorieEntry extends LogEntry {
    private double desiredCaloricIntake;

    public CalorieEntry(LocalDate date, double caloriesIntake) {
        setDate(date);
        this.desiredCaloricIntake = caloriesIntake;
    }

    public double getDesiredCaloricIntake() {
        return desiredCaloricIntake;
    }

    public void setDesiredCaloricIntake(double desiredCaloricIntake) {
        this.desiredCaloricIntake = desiredCaloricIntake;
    }

    @Override
    public String toCSVString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
        String formattedDate = getDate().format(formatter);
        return String.format(Locale.US, "%s,c,%.2f", formattedDate, desiredCaloricIntake);
    }
}
