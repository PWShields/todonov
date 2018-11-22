package com.puffinpowered.todonov.controller;

import com.puffinpowered.todonov.domain.SavedTodoItem;
import com.puffinpowered.todonov.domain.transfer.TodoItem;
import com.puffinpowered.todonov.service.TodoItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
	public SavedTodoItem create(@RequestBody TodoItem newItem) {
		return todoItemService.create(newItem);
	}

	@GetMapping
	public List<SavedTodoItem> findAll() {
		return todoItemService.findAll();
	}

	@GetMapping("/{id}")
	public SavedTodoItem findOne(@PathVariable("id") Long id) {
		return todoItemService.findOne(id);
	}

	@PutMapping("/{id}")
	public SavedTodoItem updateAndReplace(@PathVariable(value = "id") Long id, @Valid @RequestBody SavedTodoItem institution) {
		return todoItemService.updateAndReplace(institution, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") Long id) {
		todoItemService.delete(id);
	}


}
