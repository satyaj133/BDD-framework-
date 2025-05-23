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

