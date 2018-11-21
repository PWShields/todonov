Feature: CRUD for Todo Items


  Scenario: Fetch all todo items
    Given existing Todo Items
    When  all todo items are requested
    Then  all todo items are returned

  Scenario: Fetch a specific todo item
    Given the todo item exists
    When  the item id is requested by id
    Then  the item is returned

  Scenario: Mark an existing todo item as completed
    Given   an existing incomplete todo item
    When    advised the item is completed
    Then    the item is saved as completed

  Scenario: Delete an existing todo item
    Given   an existing todo item
    When    requested to delete the item
    Then    the item is no longer available


  Scenario: Attempt to fetch item that doesn't exist
    When we request an id that is not available
    Then an item not found exception occurs

  Scenario: Attempt to update an item that doesn't exist
    When we attempt to update an item that is not available
    Then an item not found exception occurs

  Scenario: Attempt to delete an item that doesn't exist
    When we attempt to delete an item that doesn't exist
    Then an itme not found exception occurs




