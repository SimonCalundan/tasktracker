package com.tasktracker.application;

import com.tasktracker.application.controller.Controller;
import com.tasktracker.application.model.Status;
import com.tasktracker.application.model.TodoItem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        switch(args.length){
            case 0 -> {
                System.out.println("Command line argument cant be null, try --help");
            }
            case 1 -> {
                if (args[0].equals("--help")){
                    displayHelp();
                }
            }
            case 2 -> {
                switch(args[0]){
                    case "add" -> {
                        Controller.createTodo(args[1]);
                    }
                    case "delete" -> {
                        Controller.removeTodo(Integer.parseInt(args[1]));
                    }
                    case "list" -> {
                        if (args[1].equals("done")) {
                            Controller.getTodos(Status.DONE).forEach(System.out::println);
                        } else if (args[1].equals("todo")){
                            Controller.getTodos(Status.TODO).forEach(System.out::println);
                        } else if (args[1].equals("in-progress")){
                            Controller.getTodos(Status.IN_PROGRESS).forEach(System.out::println);
                        } else {
                            System.out.println("Invalid filter");
                        }
                    }
                    case "update" -> {

                    }
                }
            }
        }
    }

    private static void displayHelp(){
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
