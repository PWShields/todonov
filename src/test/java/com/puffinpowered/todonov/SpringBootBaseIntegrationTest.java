package com.puffinpowered.todonov;

import com.puffinpowered.todonov.domain.SavedTodoItem;
import com.puffinpowered.todonov.domain.transfer.TodoItem;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
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
	void clean() {
		restTemplate.delete(todoEndpoint());
	}
}