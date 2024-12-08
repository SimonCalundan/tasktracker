package com.tasktracker.application.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoItem {

    private final int id;
    public static int lastId = 0;
    private String description;
    private Status status;
    private String createdAt;
    private String updatedAt;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy HH:mm:ss");


    public TodoItem() {
        // Initialize with default values
        this.id = ++lastId;
        this.description = "";
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now().format(formatter);
        this.updatedAt = LocalDateTime.now().format(formatter);
    }

    public TodoItem(String description) {
        this.id = ++lastId;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now().format(formatter);
        this.updatedAt = LocalDateTime.now().format(formatter);
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now().format(formatter);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now().format(formatter);
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%-20d  %-10s %-40s", id, status.getValue(), description);
    }
}
