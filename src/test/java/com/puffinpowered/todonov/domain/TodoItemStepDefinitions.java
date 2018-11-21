package com.puffinpowered.todonov.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puffinpowered.todonov.SpringBootBaseIntegrationTest;
import com.puffinpowered.todonov.domain.transfer.TodoItem;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
		responseEntity = post(incomingItem);
		savedTodoItem = responseEntity.getBody();
	}

	@Then("item is saved")
	public void item_is_saved() {
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("Example title", savedTodoItem.getTitle());
		assertThat(1L, is(savedTodoItem.getId()));

	}
}
