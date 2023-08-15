Feature:Adding new job in HRMS
  Background:
    When user enters valid admin username and password
    And user clicks on login button
    Then user is successfully logged in the application

    @AddJob
    Scenario: User adds a new job
      * user clicks on the admin button
      * user clicks on the job
      * user clicks on job title
      * user clicks on add button
      * user enters job "Java Instructor" title
      * user enters job description "Teaches Java"
      * user enters job note "Java programming note"
      * user clicks on the save button
      * verify the data stored in data base priperly

