package com.example.todoapp.service;

import com.example.todoapp.dto.TodoInfo;
import com.example.todoapp.dto.TodoRequest;
import com.example.todoapp.dto.TodoResponse;
import com.example.todoapp.entity.Todo;
import com.example.todoapp.exceptions.ResourceNotFoundException;
import com.example.todoapp.repository.TodoRepository;
import com.example.todoapp.utils.TodoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    TodoRepository todoRepository;

    /**
     * Adds a new todo to the database
     *
     * @param todoRequest TodoRequest
     * @return TodoResponse
     */
    @Override
    public TodoResponse createTodo(TodoRequest todoRequest) {
        Todo todo = Todo.builder()
                .title(todoRequest.getTitle())
                .description(todoRequest.getDescription())
                .date(LocalDateTime.now())
                .build();

        Todo savedTodo = todoRepository.save(todo);

        return TodoResponse.builder()
                .responseCode(TodoUtils.TODO_ADDED_CODE)
                .responseMessage(TodoUtils.TODO_ADDED_MESSAGE)
                .todoInfo(TodoInfo.builder()
                        .id(savedTodo.getId())
                        .todoTittle(savedTodo.getTitle())
                        .todoDescription(savedTodo.getDescription())
                        .build())
                .build();
    }

    /**
     * Deletes a todo from the database
     *
     * @param id Long
     * @return TodoResponse
     */
    @Override
    public TodoResponse deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id :" + id));

        todoRepository.deleteById(id);

        return TodoResponse.builder()
                .responseCode(TodoUtils.TODO_DELETE_CODE)
                .responseMessage(TodoUtils.TODO_DELETE_MESSAGE)
                .todoInfo(TodoInfo.builder()
                        .id(todo.getId())
                        .todoTittle(todo.getTitle())
                        .todoDescription(todo.getDescription())
                        .build())
                .build();
    }

    /**
     * Returns the latest 5 todos from the database
     *
     * @return List<Todo>
     */
    @Override
    public List<Todo> findLatestTodos() {

        return todoRepository.findTop5ByOrderByDateDesc();
    }


}
