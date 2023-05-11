package com.learning;

import com.learning.model.Epic;
import com.learning.model.Status;
import com.learning.model.Subtask;
import com.learning.model.Task;

import java.util.HashMap;
import java.util.Scanner;

public class Manager {
    private static final int ADD_NEW_SUBTASK = 1;
    private static final int FINISH_SUBTASK_CREATION = 2;
    private static final String HASH_MAP_IS_EMPTY = "Список задач пуст. Вначале добавьте задачу";

    Scanner scanner = new Scanner(System.in);
    HashMap<Integer, Task> storage = new HashMap<>();


    public void newTask() {
        Task task = new Task();

        System.out.println("Введите название задачи");
        task.name = scanner.nextLine();
        System.out.println("Введите описание задачи");
        task.description = scanner.nextLine();

        task.status = Status.NEW;

        System.out.println("Есть ли у этой задачи какие-то подзадачи?");
        System.out.println("1 - есть подзадачи");
        System.out.println("2 - нет подзадач");
        int command = scanner.nextInt();
        scanner.nextLine();

        if (command == ADD_NEW_SUBTASK) {
            createSubtask(task);

        } else if (command == FINISH_SUBTASK_CREATION) {
            int taskId = IdGenerator.nextId();
            storage.put(taskId, task);
            System.out.println("New task created with ID " + taskId + "\n" + task);


        } else {
            System.out.println("Выбрана неверная команда");
        }

    }

    public void addSubtask(Epic epic) {
        Subtask task = new Subtask();
        System.out.println("Введите название подзадачи");
        task.name = scanner.nextLine();
        System.out.println("Введите описание подзадачи");
        task.description = scanner.nextLine();
        task.status = Status.NEW;
        epic.tasks.add(task);
    }

    private boolean storageNotEmpty() {
        if (storage.isEmpty()) {
            System.out.println(HASH_MAP_IS_EMPTY);
        }
        return !storage.isEmpty();
    }

    public void getTasks() {
        if (storageNotEmpty()) {
            System.out.println("Список всех задач: ");
            for (Integer key : storage.keySet()) {
                System.out.println(storage.get(key).toString());
            }
        }
    }


    public void deleteAllTask() {
        if (storageNotEmpty()) {
            storage.clear();
            System.out.println("Все задачи были удалены");
        }
    }


    public void getTaskForId() {
        if (storageNotEmpty()) {
            System.out.println("Введите номер задачи, которую нужно удалить");
            int idForTask = scanner.nextInt();
            scanner.nextLine();
            if (storage.containsKey(idForTask)) {
                System.out.println(storage.get(idForTask));
            } else {
                System.out.println("Задача с ID " + idForTask + " не найдена");
            }
        }
    }

    public void deleteTaskForId() {
        if (storageNotEmpty()) {
            String idForDelete = "";
            System.out.println("Введите номер задачи");
            int idForTask = scanner.nextInt();
            scanner.nextLine();
            if (storage.containsKey(idForTask)) {
                System.out.println(storage.remove(idForTask));
                System.out.println("Задача с номером " + idForTask + " удалена");
            } else {
                System.out.println("Задача с ID " + idForTask + " не найдена");
            }
        }
    }

    void createSubtask(Task task) {
        Epic epic = new Epic(task); //передали в эпик задачу.
        addSubtask(epic);

        boolean hasMoreSubtasks = true;
        while (hasMoreSubtasks) {
            System.out.println("Добавить еще: 1 - да, 2 - нет");
            int newSubtask = scanner.nextInt();
            scanner.nextLine();
            if (newSubtask == ADD_NEW_SUBTASK) {
                addSubtask(epic);
            } else {
                hasMoreSubtasks = false;
            }
        }

        int taskId = IdGenerator.nextId();
        storage.put(taskId, epic);
        System.out.println("New epic created with ID " + taskId + "\n" + epic);
    }

    public void updateTask() {
        if (storageNotEmpty()) {
            new UpdateTask(this).updateTask();
            // UpdateTask updateTask = new UpdateTask();
            //updateTask.updateTask();
        }
    }
}