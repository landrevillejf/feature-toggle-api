Feature: Feature Toggle API

  Scenario: Get all feature toggles
    Given the application is running
    When I make a GET request to "/feature-toggle-api/features"
    Then the response status code should be 200
    And the response should contain JSON
