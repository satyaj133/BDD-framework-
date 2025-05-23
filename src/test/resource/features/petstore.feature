Feature: Petstore API endpoint tests

  Scenario: Add a new pet successfully (Positive)
    Given the pet payload with id 101, name "Buddy", and status "available"
    When I send a POST request to "/pet"
    Then the response status code should be 200
    And the response should contain the pet with name "Buddy"

  Scenario: Add a pet without a name (Negative)
    Given the pet payload with id 102 and missing name field
    When I send a POST request to "/pet"
    Then the response status code should be 400




    Feature: Petstore API Tests

  Scenario: Get pet by valid ID (Positive)
    Given the pet ID is "12345"
    When I send a GET request to /pet/{petId}
    Then I receive a 200 status code

  Scenario: Get pet with invalid ID (Negative)
    Given the pet ID is "999999"
    When I send a GET request to /pet/{petId}
    Then I receive a 404 status code

