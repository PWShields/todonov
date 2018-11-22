package com.puffinpowered.todonov;

import com.puffinpowered.todonov.domain.SavedTodoItem;
import com.puffinpowered.todonov.domain.transfer.TodoItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class SpringBootBaseIntegrationTest {

	private static final Logger log = LoggerFactory.getLogger(SpringBootBaseIntegrationTest.class);

	private final String SERVER_URL = "http://localhost";
	private final String TODO_ENDPOINT = "/todo";

	protected RestTemplate restTemplate;

	@LocalServerPort
	protected int port;

	public SpringBootBaseIntegrationTest() {
		restTemplate = new RestTemplate();
	}

	private String todoEndpoint() {
		return SERVER_URL + ":" + port + TODO_ENDPOINT;
	}

	protected int put(final TodoItem something) {
		return restTemplate.postForEntity(todoEndpoint(), something, Void.class).getStatusCodeValue();
	}

	 protected ResponseEntity<SavedTodoItem> post(final TodoItem newItem){
		return restTemplate.postForEntity(todoEndpoint(),newItem, SavedTodoItem.class);
	 }

	protected ResponseEntity<List<SavedTodoItem>> getAll() {
		return restTemplate.exchange(
				todoEndpoint(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<SavedTodoItem>>() {
				});
	}

	protected SavedTodoItem getById(Long id){
		return restTemplate.getForObject(todoEndpoint()+"/"+id, SavedTodoItem.class);
	}

	void clean() {
		restTemplate.delete(todoEndpoint());
	}
}