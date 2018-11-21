package com.puffinpowered.todonov.service;


import com.puffinpowered.todonov.domain.SavedTodoItem;
import com.puffinpowered.todonov.domain.transfer.TodoItem;

public interface TodoItemService {
	SavedTodoItem create(TodoItem newItem);
}
