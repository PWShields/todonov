package com.puffinpowered.todonov.service;


import com.puffinpowered.todonov.domain.SavedTodoItem;
import com.puffinpowered.todonov.domain.transfer.TodoItem;

import javax.validation.Valid;
import java.util.List;

public interface TodoItemService {
	SavedTodoItem create(TodoItem newItem);

	List<SavedTodoItem> findAll();

	SavedTodoItem findOne(Long id);

	SavedTodoItem updateAndReplace(@Valid SavedTodoItem institution, Long id);

	void delete(Long id);
}
