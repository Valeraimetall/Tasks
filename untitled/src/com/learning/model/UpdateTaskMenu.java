package com.learning.model;

import java.util.Arrays;

public enum UpdateTaskMenu {
    EXIT(0, "Выйти"),
    UPDATE_NAME(1, "Изменить имя задачи"),
    UPDATE_DESCRIPTION(2, "Изменить описание задачи"),
    UPDATE_STATUS(3, "Обновить статус");

    public int number;
    public String description;

    UpdateTaskMenu(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public static UpdateTaskMenu getMenuById(Integer number) {
        return Arrays.stream(UpdateTaskMenu.values()).filter(menu -> menu.number == number).findFirst().orElse(EXIT);
    }
}