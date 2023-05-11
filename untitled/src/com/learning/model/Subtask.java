package com.learning.model;//Каждая подзадача знает в рамках какого эпика она выполняется
//Завершение всех подзадач является завершением эпика

public class Subtask extends Task {

    public Subtask(String name, String description, Status status) {

        super(name, description, status);
    }

    public Subtask() {

    }
}