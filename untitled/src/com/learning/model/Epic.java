package com.learning.model;

import java.util.ArrayList;
import java.util.List;

//Большая задача, которая делится на подзадачи. Каждый эпик знает каке подзадачи в него входят
public class Epic extends Task {
    public List<Subtask> tasks = new ArrayList<Subtask>();

    public Epic(String name, String description, Status status) {

        super(name, description, status);
    }

    public Epic(Task task) { //задачу task сделали epic
        this.name = task.name;
        this.description = task.description;
        this.status = task.status;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "tasks=" + tasks +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}