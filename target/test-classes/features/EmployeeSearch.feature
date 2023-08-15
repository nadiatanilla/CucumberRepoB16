Feature: Search an employee
  Background:
   # Given user is navigated to HRMS application
    When user enters valid admin username and password
    And user clicks on login button
    Then user is successfully logged in the application
    When user clicks on PIM option and Employee list option


@smoke @regression @sprint3 @background
  Scenario: Search employee by id
    And user enters valid employee id
    And user clicks on search button
    Then user is able to see employee information

@regression @sprint2 @smoke @background
    Scenario: Search employee by name

      And user enters valid employee name in name text box
      And user clicks on search button
      Then user is able to see employee information