package com.puffinpowered.todonov.domain;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/CreateTodo.feature", "src/test/resources/features/GetTodo.feature"}, plugin = {"pretty", "html:target/cucumber"})
public class TodoItemTest {
}
