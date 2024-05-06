package edu.rit.croatia.swen383.g1.dm.model;

import java.time.LocalDate;

public abstract class LogEntry {
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date2) {
        this.date = date2;
    }

    // Keep as abstract to force implementation in subclasses
    public abstract String toCSVString();

}
