package com.puffinpowered.todonov.controller;

import com.puffinpowered.todonov.domain.SavedTodoItem;
import com.puffinpowered.todonov.domain.transfer.TodoItem;
import com.puffinpowered.todonov.service.TodoItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoItemController {

	private static final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

	TodoItemService todoItemService;

	public TodoItemController(TodoItemService todoItemService) {
		this.todoItemService = todoItemService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SavedTodoItem create(@RequestBody TodoItem newItem){
		return todoItemService.create(newItem);
	}

}
