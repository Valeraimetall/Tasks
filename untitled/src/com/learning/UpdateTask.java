package com.learning;

import com.learning.model.Status;
import com.learning.model.Task;
import com.learning.model.UpdateTaskMenu;

public class UpdateTask {
    private static final String IS_STRING_A_NUMBER_REGEXP = "-?\\d+";
    private static final String TASK_NOT_FOUND_WITH_ID = "Не найдена задача с номером";
    private static final String CHOOSE_ID = "Выберите ID задачи, которую надо обновить";
    private static final String CHOOSE_COMMAND = "Выберите команду";
    private static final String COMPLETED = "Редактирование задачи завершено\n\n";
    private static final String CURRENT_STATUS = "Текущий статус";
    private static final String CHOOSE_NEW_STATUS = "Выберите новый статус";

    private static final String CURRENT_NAME = "Текущее имя";
    private static final String ENTER_NEW_NAME = "Введите новое имя";

    private static final String CURRENT_DESCRIPTION = "Текущее описание";
    private static final String ENTER_NEW_DESCRIPTION = "Введите новое описание";

    private final Manager manager;

    private int taskId;
    private Task taskToUpdate;
    private int currentCommand;
    private String currentInput;

    public UpdateTask(Manager manager) {

        this.manager = manager;
    }

    public void updateTask() {
        printTasks();
        getNextCommand();

        if (!manager.storage.containsKey(currentCommand)) {
            System.out.println(TASK_NOT_FOUND_WITH_ID + " " + currentCommand);
        }

        taskToUpdate = manager.storage.get(currentCommand);
        taskId = currentCommand;

        printTaskEditMenu();
        getNextCommand();

        UpdateTaskMenu menu = UpdateTaskMenu.getMenuById(currentCommand);


        switch (menu) {
            case EXIT:
                System.out.println(COMPLETED);
                break;
            case UPDATE_NAME:
                updateTaskName();
                break;
            case UPDATE_DESCRIPTION:
                updateDescription();
                break;
            case UPDATE_STATUS:
                updateStatus();
                break;
        }

    }

    private void updateStatus() {
        System.out.println(CURRENT_STATUS + ": " + taskToUpdate.status);
        System.out.println(CHOOSE_NEW_STATUS);
        printStatusEditMenu();
        getNextCommand();

        Status menuStatus = Status.getStatusMenuById(currentCommand);

        switch (menuStatus) {
            case EXIT: {
                System.out.println(COMPLETED);
            } break;
            case NEW: {
                taskToUpdate.status = Status.NEW;
                System.out.println(taskToUpdate);
                System.out.println(COMPLETED);
            } break;
            case IN_PROGRESS: {
                taskToUpdate.status = Status.IN_PROGRESS;
                System.out.println(taskToUpdate);
                System.out.println(COMPLETED);
            } break;
            case DONE: {
                taskToUpdate.status = Status.DONE;
                System.out.println(taskToUpdate);
                System.out.println(COMPLETED);
            } break;
        }

    }

    private void updateDescription() {
        System.out.println(CURRENT_DESCRIPTION + ": " + taskToUpdate.description);
        System.out.println(ENTER_NEW_DESCRIPTION);
        getUserInput();
        taskToUpdate.description = currentInput;
        manager.storage.put(taskId, taskToUpdate);
        System.out.println(taskToUpdate);
        System.out.println(COMPLETED);
    }

    private void updateTaskName() {
        System.out.println(CURRENT_NAME + ": " + taskToUpdate.name);
        System.out.println(ENTER_NEW_NAME);
        getUserInput();
        taskToUpdate.name = currentInput;
        manager.storage.put(taskId, taskToUpdate);
        System.out.println(taskToUpdate);
        System.out.println(COMPLETED);
    }

    /**
     * Получение следующей числовой команды.
     * Не предназначено для ввода данных (см. getUserInput())
     */
    private void getNextCommand() {
        currentInput = manager.scanner.next();

        // Проверяем, что ввод - число
        if (currentInput.matches(IS_STRING_A_NUMBER_REGEXP)) {
            currentCommand = Integer.parseInt(currentInput);
        } else {
            // Повторяем рекурсивно, пока пользователь не ввел число
            // Рекурсия в целом не самое лучшее решение для пользовательского ввода, но в данном случае лаконичнее.
            System.out.println("Пожалуйста, введите число");
            getNextCommand();
        }
    }

    /**
     * Получение ползовательского ввода.
     */
    private void getUserInput() {
        // Дочитываем предыдущую строку
        manager.scanner.nextLine();
        // Считываем новую строку
        currentInput = manager.scanner.nextLine();
    }

    private void printTaskEditMenu() {
        System.out.println(CHOOSE_COMMAND);
        for(UpdateTaskMenu updateTaskMenu : UpdateTaskMenu.values()) {
            System.out.println(updateTaskMenu.number + " - " + updateTaskMenu.description);
        }
    }

    private void printTasks() {
        System.out.println(CHOOSE_ID);
        for (Integer taskId : manager.storage.keySet()) {
            System.out.println(taskId + ": " + manager.storage.get(taskId));
        }
    }

    private void printStatusEditMenu() {
        for(Status status : Status.values()) {
            System.out.println(status.number + " - " + status.description);
        }
    }
}