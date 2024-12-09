package com.tasktracker.application.controller;

import com.tasktracker.application.model.TodoItem;
import com.tasktracker.storage.StorageHandler;
import net.jqwik.api.*;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerJqwikTest {

    @BeforeEach
    void clearData(){
        StorageHandler.clearTodos();
    }

    @Property
    void testTodoDescription(@ForAll String description){
        TodoItem todo = Controller.createTodo(description);
    }


    @Property
    void testUpdateTodoDescription(@ForAll int id, @ForAll String newDescription){
        TodoItem todo = Controller.createTodo("Initial description");

        Controller.updateTodoItemDescription(todo.getId(), newDescription);

        TodoItem updatedTodo = Controller.findTodo(todo.getId());
        assertNotNull(updatedTodo);
        assertEquals(updatedTodo.getDescription(), newDescription);
    }
}
