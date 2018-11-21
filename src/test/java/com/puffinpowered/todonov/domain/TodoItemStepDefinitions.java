package com.puffinpowered.todonov.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puffinpowered.todonov.SpringBootBaseIntegrationTest;
import com.puffinpowered.todonov.domain.transfer.TodoItem;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class TodoItemStepDefinitions extends SpringBootBaseIntegrationTest {

	TodoItem incomingItem;

	SavedTodoItem savedTodoItem;

	ResponseEntity<SavedTodoItem> responseEntity;

	@Given("an incoming todo item")
	public void an_incoming_todo_item() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<TodoItem> typeReference = new TypeReference<TodoItem>() {
		};
		InputStream inputStream = new ClassPathResource("resources/data/newItem.json").getInputStream();

		incomingItem = mapper.readValue(inputStream, typeReference);
	}

	@When("todo item is processed")
	public void todo_item_is_processed() {
		ResponseEntity<SavedTodoItem> responseEntity = restTemplate.postForEntity("http://localhost/todo", incomingItem, SavedTodoItem.class);
		savedTodoItem = responseEntity.getBody();
	}

	@Then("item is saved")
	public void item_is_saved() {
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("Example title", savedTodoItem.getTitle());
		assertEquals(1L, java.util.Optional.ofNullable(savedTodoItem.getId()));

	}
}
