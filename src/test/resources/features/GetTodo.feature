Feature: Retrieve Todo Items


  Scenario: Fetch all todo items
    Given existing Todo Items
    When  all todo items are requested
    Then  all todo items are returned

  Scenario: Fetch a specific todo item
    Given the todo item exists
    When  the item id is requested by id
    Then  the item is returned



