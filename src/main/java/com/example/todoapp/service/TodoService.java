package com.example.todoapp.service;

import com.example.todoapp.dto.TodoRequest;
import com.example.todoapp.dto.TodoResponse;
import com.example.todoapp.entity.Todo;

import java.util.List;

public interface TodoService {
    TodoResponse createTodo(final TodoRequest todoRequest);
    TodoResponse deleteTodo(final Long id);
    List<Todo> findLatestTodos();
}
