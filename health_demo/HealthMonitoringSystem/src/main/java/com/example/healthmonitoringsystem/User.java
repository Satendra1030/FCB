package com.example.healthmonitoringsystem;

public class User {
    private static int idCounter = 1;
    private int id;
    private String name;

    public User(String name) {
        this.id = idCounter++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
