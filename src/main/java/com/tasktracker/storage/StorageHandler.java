package com.tasktracker.storage;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.tasktracker.application.model.Status;
import com.tasktracker.application.model.TodoItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageHandler {
    private static final String FILE_PATH;

    // Be sure to add your own .env file containing the path to your .json file
    static {
        Dotenv dotenv = Dotenv.load();
        FILE_PATH = dotenv.get("FILE_PATH");
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static void addTodo(TodoItem todo) {
        List<TodoItem> todos = getTodos();
        todos.add(todo);
        saveTodos(todos);
    }

    public static void removeTodo(int id) {
        List<TodoItem> todos = getTodos();
        todos.removeIf(todo -> todo.getId() == id);
        saveTodos(todos);
    }

    public static List<TodoItem> getTodos() {
        // Use the FILE_PATH constant HERE to load the resource
        File file = new File(FILE_PATH);

        try {
            InputStream inputStream = new FileInputStream(file);
            return objectMapper.readValue(inputStream, new TypeReference<List<TodoItem>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateTodoStatus(int id, Status status){
        List<TodoItem> todos = getTodos();

        if(todos != null){
            for (var t : todos){
                if(t.getId() == id){
                    t.setStatus(status);
                    saveTodos(todos);
                    return;
                }
            }
        }
        System.out.println("Todo with ID " + id + " not found");
    }

    public static void updateTodoDescription(int id, String newDescription){
        List<TodoItem> todos = getTodos();

        if(todos != null){
            for (var t : todos){
                if(t.getId() == id){
                    t.setDescription(newDescription);
                    saveTodos(todos);
                    return;
                }
            }
        }
        System.out.println("Todo with ID " + id + " not found");
    }

    public static void saveTodos(List<TodoItem> todos){
        try {
            File file = new File(FILE_PATH);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, todos);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearTodos(){
       saveTodos(new ArrayList<>());
    }
}
