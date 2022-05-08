Feature: The user want to see all the available pets in the website

  Scenario: User wants to retrieve all the available pets in the application
    Given the user is on the website
    When retrieve all the available pets
    Then all the pets in the response must be available
