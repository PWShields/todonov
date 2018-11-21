package com.puffinpowered.todonov.domain.transfer;

import com.puffinpowered.todonov.domain.SavedTodoItem;
import org.springframework.stereotype.Component;

@Component
public class TodoAssembler {

	public SavedTodoItem disAssemble(TodoItem newItem) {
		return new SavedTodoItem()
				.setCompleted(false)
				.setTitle(newItem.getTitle())
				.setUrl("");
	}
}
