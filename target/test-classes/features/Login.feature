Feature: Login related scenarios
  Background:
   # Given user is navigated to HRMS application
#   untill flow  broken


  @smoke @regression @login1 @sprint1
  Scenario: Valid admin login

    When user enters valid admin username and password
    And user clicks on login button
    Then user is successfully logged in the application

    @employee @sprint1
    Scenario: valid ess login

      When user enters ess username and password
      And user clicks on login button
      Then user is successfully logged in the application

      @invalid @sprint1

      Scenario: invalid login

        When user enters invalid admin username and password
        And user clicks on login button
        Then error message is displayed

  @negative
  Scenario Outline: negative login test
    When user enters "<username>" and "<password>" and verifying the "<error>" for the combinations
    Examples:
      | username | password | error |
      |admin     |fkfkkkj   |Invalid credentials|
      |admin1    |Hum@nhrm123|Invalid credentials|
      |          |Hum@nhrm123|Username cannot be empty|
      |admin     |           |Password cannot be empty|