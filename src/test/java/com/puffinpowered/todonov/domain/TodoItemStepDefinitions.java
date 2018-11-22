package com.puffinpowered.todonov.domain;

import com.puffinpowered.todonov.SpringBootBaseIntegrationTest;
import com.puffinpowered.todonov.domain.transfer.TodoItem;
import com.puffinpowered.todonov.repository.TodoItemRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TodoItemStepDefinitions extends SpringBootBaseIntegrationTest {

	TodoItem incomingItem;

	SavedTodoItem savedTodoItem;

	ResponseEntity<SavedTodoItem> responseEntity;

	@Autowired
	TodoItemRepository todoItemRepository;

	List<SavedTodoItem> savedTodoItemList;

	SavedTodoItem lastItem;


	@Given("an incoming todo item")
	public void an_incoming_todo_item() throws IOException {

		incomingItem = new TodoItem();
		incomingItem.setTitle("Example title");
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

	@Given("existing Todo Items")
	public void existing_Todo_Items() throws IOException {
		SavedTodoItem item1 = new SavedTodoItem()
				.setCompleted(false)
				.setTitle("First todo")
				.setUrl("/todo/1");
		SavedTodoItem item2 = new SavedTodoItem()
				.setCompleted(false)
				.setTitle("Second todo")
				.setUrl("/todo/2");
		List<SavedTodoItem> items = Arrays.asList(item1, item2);
		todoItemRepository.saveAll(items);
	}

	@When("all todo items are requested")
	public void all_todo_items_are_requested() {
		savedTodoItemList = getAll().getBody();
	}

	@Then("all todo items are returned")
	public void all_todo_items_are_returned() {
		assertThat(savedTodoItemList.size(), is(3));
	}

	@Given("the todo item exists")
	public void the_todo_item_exists() {
		SavedTodoItem newItem = new SavedTodoItem()
				.setCompleted(false)
				.setTitle("Last todo")
				.setUrl("/todo/4");
		todoItemRepository.save(newItem);
	}

	@When("the item id is requested by id")
	public void the_item_id_is_requested_by_id() {
		lastItem = getById(4l);
	}

	@Then("the item is returned")
	public void the_item_is_returned() {
		assertThat(lastItem.getId(), is(4L));
		assertThat(lastItem.getTitle(), is("Last todo"));
		assertThat(lastItem.getUrl(), is("/todo/4"));
	}
}
