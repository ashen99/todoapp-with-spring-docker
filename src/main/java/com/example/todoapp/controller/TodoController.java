package com.example.todoapp.controller;

import com.example.todoapp.dto.TodoRequest;
import com.example.todoapp.dto.TodoResponse;
import com.example.todoapp.entity.Todo;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping("/add-todo")
    public TodoResponse addTodo(@RequestBody TodoRequest todoRequest) {
        return todoService.createTodo(todoRequest);
    }

    @DeleteMapping("/delete/{id}")
    public TodoResponse deleteTodo(@PathVariable("id") Long id){
        return todoService.deleteTodo(id);
    }

    @GetMapping("/findLatest")
    public List<Todo> findLatestTodos(){
        return todoService.findLatestTodos();
    }
}
