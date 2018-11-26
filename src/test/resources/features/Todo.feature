Feature: CRUD for Todo Items
# Business Capability: create, read and update Todo_Items

  Scenario: Mark an existing todo item as completed
    Given   an existing incomplete todo item
    When    advised the item is completed
    Then    the item is saved as completed

  Scenario: Delete an existing todo item
    Given   an existing todo item
    When    requested to delete the item
    Then    the item is no longer available



