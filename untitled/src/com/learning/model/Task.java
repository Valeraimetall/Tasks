package com.learning.model;

public class Task {
    public String name;
    public String description;
    public Status status;

    public Task() {

    }

    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Задача: " + name + '\'' +
                ", имеет статус ='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}