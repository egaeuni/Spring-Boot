package com.ll.demo01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private long todoLastId;
    private List<Todo> todos;

    public TodoController() {
        todos = new ArrayList<>();
    }

    @GetMapping("")
    @ResponseBody
    public List<Todo> getTodos() {
        return todos;
    }

    @GetMapping("/detail")
    @ResponseBody
    public Todo getTodo(
            long id
    ) {
        return todos
                .stream()
                .filter(
                        todo -> todo.getId() == id
                )
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Todo getTodo2(
            @PathVariable long id
            ) {
        return todos
                .stream()
                .filter(
                        todo -> todo.getId() == id
                )
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/add")
    @ResponseBody
    public Todo add(
            String body
    ) {
        Todo todo = Todo.
                builder()
                .id(++todoLastId)
                .body(body)
                .build();

        todos.add(todo);

        return todo;
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public boolean remove(
            @PathVariable long id
    ) {
        boolean removed = todos.removeIf((todo -> todo.getId() == id));

        return removed;
    }

    @GetMapping("/modify/{id}")
    @ResponseBody
    public boolean modify(
            @PathVariable long id,
            String body
    ) {
        Todo todo = todos
                .stream()
                .filter(
                        _todo -> _todo.getId() == id
                )
                .findFirst()
                .orElse(null);

        if( todo == null ) return false;

        todo.setBody(body);

        return true;
    }
}
