package com.example.healthmonitoringsystem;

import java.util.ArrayList;
import java.util.List;

public class HealthMonitoringServer {
    private List<User> users;
    private List<HealthRecord> healthRecords;

    public HealthMonitoringServer() {
        users = new ArrayList<>();
        healthRecords = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addHealthRecord(HealthRecord healthRecord) {
        healthRecords.add(healthRecord);
    }

    public List<HealthRecord> getHealthRecords() {
        return healthRecords;
    }

    public List<User> getUsers() {
        return users;
    }
}
