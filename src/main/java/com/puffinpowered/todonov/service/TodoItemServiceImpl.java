package com.puffinpowered.todonov.service;

import com.puffinpowered.todonov.controller.TodoItemController;
import com.puffinpowered.todonov.domain.SavedTodoItem;
import com.puffinpowered.todonov.domain.transfer.TodoAssembler;
import com.puffinpowered.todonov.domain.transfer.TodoItem;
import com.puffinpowered.todonov.repository.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TodoItemServiceImpl implements TodoItemService {

	private static final Logger logger = LoggerFactory.getLogger(TodoItemServiceImpl.class);

	TodoItemRepository todoItemRepository;

	TodoAssembler todoAssembler;

	public TodoItemServiceImpl(TodoItemRepository todoItemRepository, TodoAssembler todoAssembler) {
		this.todoItemRepository = todoItemRepository;
		this.todoAssembler = todoAssembler;
	}

	@Override
	public SavedTodoItem create(TodoItem newItem) {
		return todoItemRepository.save(todoAssembler.disAssemble(newItem));
	}
}
