package com.tasktracker.application.controller;

import com.tasktracker.application.model.Status;
import com.tasktracker.application.model.TodoItem;
import com.tasktracker.storage.StorageHandler;

import java.util.List;

public class Controller {

    public static TodoItem createTodo(String description) {
        TodoItem todo = new TodoItem(description);
        StorageHandler.addTodo(todo);
        return todo;
    }

    public static void updateTodoItemDescription(int id, String newDescription) {
        StorageHandler.updateTodoDescription(id, newDescription);
    }

    public static void updateTodoItemStatus(int id, Status status) {
        if (status.equals(Status.TODO)){
            StorageHandler.updateTodoStatus(id, Status.TODO);
        } else if (status.equals(Status.IN_PROGRESS)){
            StorageHandler.updateTodoStatus(id, Status.IN_PROGRESS);
        } else if (status.equals(Status.DONE)){
            StorageHandler.updateTodoStatus(id, Status.DONE);
        }
    }

    public static TodoItem findTodo(int id) {
        for (var t : StorageHandler.getTodos()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public static boolean removeTodo(int id) {
        StorageHandler.removeTodo(id);
        return true;
    }

    public static List<TodoItem> getTodos() {
        return StorageHandler.getTodos();
    }

    public static List<TodoItem> getTodos(Status status){
      return StorageHandler.getTodos().stream()
              .filter(todo -> todo.getStatus().equals(status))
              .toList();
    }

}
