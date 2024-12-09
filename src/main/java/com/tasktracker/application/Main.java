package com.tasktracker.application;

import com.tasktracker.application.controller.Controller;
import com.tasktracker.application.model.Status;

public class Main {
    public static void main(String[] args) {
        switch (args.length) {
            case 0 -> System.out.println("Command line argument cant be null, try --help");
            case 1 -> {
                switch (args[0]) {
                    case "--help" -> displayHelp();
                    case "list" -> handleList("");
                }
            }
            case 2 -> {
                switch (args[0]) {
                    case "add" -> Controller.createTodo(args[1]);
                    case "delete" -> Controller.removeTodo(Integer.parseInt(args[1]));
                    case "list" -> handleList(args[1]);
                    case "mark-in-progress" -> handleUpdateStatus(args[1], Status.IN_PROGRESS);
                    case "mark-done" -> handleUpdateStatus(args[1], Status.DONE);
                }
            }
            case 3 -> {
                switch (args[0]) {
                    case "update" -> handleUpdate(args[1], args[2]);
                    case "mark-in-progress" -> handleUpdateStatus(args[1], Status.IN_PROGRESS);

                }
            }
        }
    }

    public static void handleUpdateStatus(String input, Status status) {
        try {
            int id = Integer.parseInt(input);
            Controller.updateTodoItemStatus(id, status);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input");
        }
    }

    private static void handleUpdate(String input, String newDescription) {
        try {
            int id = Integer.parseInt(input);
            if (newDescription == null) throw new RuntimeException("Input cant be null");
            Controller.updateTodoItemDescription(id, newDescription);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleList(String input) {
        switch (input) {
            case "done" -> Controller.getTodos(Status.DONE).forEach(System.out::println);
            case "todo" -> Controller.getTodos(Status.TODO).forEach(System.out::println);
            case "in-progress" -> Controller.getTodos(Status.IN_PROGRESS).forEach(System.out::println);
            default -> Controller.getTodos().forEach(System.out::println);
        }
    }

    private static void displayHelp() {
        String helpText = """
                Usage: task-cli [command] [arguments]
                
                Commands:
                  # Adding a new task
                  task-cli add "Buy groceries"
                  # Output: Task added successfully (ID: 1)
                
                  # Updating and deleting tasks
                  task-cli update [task ID] "Updated description"
                  task-cli delete [task ID]
                
                  # Marking a task as in progress or done
                  task-cli mark-in-progress [task ID]
                  task-cli mark-done [task ID]
                
                  # Listing all tasks
                  task-cli list
                
                  # Listing tasks by status
                  task-cli list done
                  task-cli list todo
                  task-cli list in-progress
                
                Use these commands to manage your tasks effectively.
                """;
        System.out.println(helpText);
    }
}
