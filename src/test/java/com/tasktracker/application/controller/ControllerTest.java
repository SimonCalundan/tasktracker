package com.tasktracker.application.controller;

import com.tasktracker.application.model.TodoItem;
import com.tasktracker.storage.StorageHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
public class ControllerTest {
    @BeforeEach
    public void setup(){
        StorageHandler.clearTodos();
    }

    @Test
    public void testCreateTodo(){
        TodoItem todo = Controller.createTodo("Hent cykel");
        assertNotNull(todo);
        assertEquals("Hent cykel", todo.getDescription());
    }


    @Test
    public void testUpdateTodo(){
        TodoItem todo = Controller.createTodo("Hent cykel");
        Controller.updateTodoItemDescription(todo.getId(), "Hent cykel NU");

        TodoItem updatedTodo = Controller.findTodo(todo.getId());
        assertNotNull(updatedTodo);
        assertEquals("Hent cykel NU", updatedTodo.getDescription());
    }

    @Test
    public void testRemoveTodo(){
        TodoItem todo = Controller.createTodo("Hent cykel");
        boolean removed = Controller.removeTodo(todo.getId());
        assertTrue(removed);

        TodoItem removedTodo = Controller.findTodo(todo.getId());
        assertNull(removedTodo);
    }

    @Test
    public void getTodos(){
        Controller.createTodo("Hent cykel");
        Controller.createTodo("Gør rent");

        List<TodoItem> todos = Controller.getTodos();
        assertEquals(2, todos.size());
    }
}
