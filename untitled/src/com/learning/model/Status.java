package com.learning.model;

import java.util.Arrays;

public enum Status {
    EXIT(0, "Выйти"),
    NEW(1, "Новая задача"),
    IN_PROGRESS(2, "Задача выполняется"),
    DONE(3, "Задача выполнена");

    public int number;
    public String description;

    Status(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public static Status getStatusMenuById(Integer number) {
        return Arrays.stream(Status.values()).filter(menu -> menu.number == number).findFirst().orElse(EXIT);
    }
}