package com.puffinpowered.todonov.service;


import com.puffinpowered.todonov.domain.SavedTodoItem;
import com.puffinpowered.todonov.domain.transfer.TodoItem;

import java.util.List;

public interface TodoItemService {
	SavedTodoItem create(TodoItem newItem);

	List<SavedTodoItem> findAll();

	SavedTodoItem findOne(Long id);

	SavedTodoItem updateAndReplace(Boolean isComplete, Long id);

	void delete(Long id);
}
