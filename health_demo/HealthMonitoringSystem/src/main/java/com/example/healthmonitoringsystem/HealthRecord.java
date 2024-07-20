package com.example.healthmonitoringsystem;

import java.time.LocalDateTime;

public class HealthRecord {
    private static int idCounter = 1;
    private int id;
    private int userId;
    private double weight;
    private String exercise;
    private LocalDateTime timestamp;

    public HealthRecord(int userId, double weight, String exercise) {
        this.id = idCounter++;
        this.userId = userId;
        this.weight = weight;
        this.exercise = exercise;
        this.timestamp = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public double getWeight() {
        return weight;
    }

    public String getExercise() {
        return exercise;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
