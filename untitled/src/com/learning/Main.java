package com.learning;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            int command = scanner.nextInt();

            Menu menu = Menu.getMenuById(command);

            switch (menu) {
                case UNKNOWN:
                    System.out.println("Unknown command");
                    break;
                case CREATE_TASK:
                    manager.newTask();
                    break;
                case GET_TASKS:
                    manager.getTasks();
                    break;
                case DELETE_ALL_TASKS:
                    manager.deleteAllTask();
                    break;
                case UPDATE_TASK:
                    manager.updateTask();
                    break;
                case GET_TASK_FOR_ID:
                    manager.getTaskForId();
                    break;
                case DELETE_TASK_FOR_ID:
                    manager.deleteTaskForId();
                    break;
                case EXIT:
                    isRunning = false;
                    break;
            }
        }
        System.out.println("Программа завершена");
    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Создать задачу");
        System.out.println("2 - Получить список всех задач");
        System.out.println("3 - Удалить все задачи");
        System.out.println("4 - Получить задачу по номеру");
        System.out.println("5 - Обновить задачу");
        System.out.println("6 - Удалить задачу по номеру");
        System.out.println(("0 - Завершить работу"));
    }
}