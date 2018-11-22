package com.puffinpowered.todonov.service;

import com.puffinpowered.todonov.domain.SavedTodoItem;
import com.puffinpowered.todonov.domain.transfer.TodoAssembler;
import com.puffinpowered.todonov.domain.transfer.TodoItem;
import com.puffinpowered.todonov.repository.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService {

	final static String NOT_FOUND = "Item does not exist";
	private static final Logger logger = LoggerFactory.getLogger(TodoItemServiceImpl.class);
	TodoItemRepository todoItemRepository;
	TodoAssembler todoAssembler;

	public TodoItemServiceImpl(TodoItemRepository todoItemRepository, TodoAssembler todoAssembler) {
		this.todoItemRepository = todoItemRepository;
		this.todoAssembler = todoAssembler;
	}

	@Override
	public SavedTodoItem create(TodoItem newItem) {
		SavedTodoItem savedItem = todoItemRepository.save(todoAssembler.disAssemble(newItem));
		savedItem.setUrl("/todo/"+savedItem.getId());
		return todoItemRepository.save(savedItem);
	}

	@Override
	public List<SavedTodoItem> findAll() {
		return (List<SavedTodoItem>) todoItemRepository.findAll();
	}

	@Override
	public SavedTodoItem findOne(Long id) {
		return todoItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND));
	}

	@Override
	public SavedTodoItem updateAndReplace(@Valid SavedTodoItem item, Long id) {
		todoItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND));
		item.setId(id);
		return todoItemRepository.save(item);
	}

	@Override
	public void delete(Long id) {
		todoItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND));
		todoItemRepository.deleteById(id);
	}
}
