package com.puffinpowered.todonov.repository;

import com.puffinpowered.todonov.domain.SavedTodoItem;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TodoItemRepository extends PagingAndSortingRepository<SavedTodoItem, Long>{
}
