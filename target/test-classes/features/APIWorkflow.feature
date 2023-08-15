Feature:Syntax API HRMS Flow

  Background:
    Given a JWT is generated
   @API
  Scenario: Creating the employee using API

     Given a request is prepared for creating an employee
     When a post call is made to create an employee
     Then the status code for creating employee is 201
     And the employee created contains key "Message" and value "Employee Created"
     And the employee id "Employee.employee_id" is stored as global variable
     @API
     Scenario:Get the created employee
       Given a request is prepared for retrieving an employee
       When a GET call is made to retrieve the employee
       Then the status code for this employee is 200
       And the employee id "employee.employee_id" must match with globally stored employee id
       And this employee data at "employee" object matches with the data used to create one employee
       |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
       |justin       |azzuri      |ms             |Male      |2000-07-21  |Happy     |QA           |
@API
       Scenario: Creating Employee using jsonBody

         Given a request is prepared for creating an employee using json payload
         When a post call is made to create an employee
         Then the status code for creating employee is 201
         And the employee created contains key "Message" and value "Employee Created"
         And the employee id "Employee.employee_id" is stored as global variable
@dynamic
  Scenario: Creating an employee using highly dynamic scenario

  Given a request is prepared for creating an employee with data "justin" , "azzuri" , "ms" , "M" , "2000-07-21" , "confirmed" , "QA"
    When a post call is made to create an employee
    Then the status code for creating employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global variable
