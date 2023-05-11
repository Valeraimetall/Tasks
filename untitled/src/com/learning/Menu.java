package com.learning;

import java.util.Arrays;

public enum Menu {
    UNKNOWN(-1),
    EXIT(0),
    CREATE_TASK(1),
    GET_TASKS(2),
    DELETE_ALL_TASKS(3),
    GET_TASK_FOR_ID(4),
    UPDATE_TASK(5),
    DELETE_TASK_FOR_ID(6);

    int number;

    Menu(int number) {
        this.number = number;
    }

    public static Menu getMenuById(Integer number) {
        return Arrays.stream(Menu.values()).filter(menu -> menu.number == number).findFirst().orElse(EXIT);
    }
}