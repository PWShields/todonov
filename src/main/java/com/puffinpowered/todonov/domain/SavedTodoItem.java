package com.puffinpowered.todonov.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class SavedTodoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String url;

	private Boolean completed;

	public SavedTodoItem setTitle(String title) {
		this.title = title;
		return this;
	}

	public SavedTodoItem setUrl(String url) {
		this.url = url;
		return this;
	}

	public SavedTodoItem setCompleted(Boolean completed) {
		this.completed = completed;
		return this;
	}
}
