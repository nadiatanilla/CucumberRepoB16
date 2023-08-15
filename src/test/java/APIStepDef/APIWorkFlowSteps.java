package APIStepDef;

import Utils.APIConstantsClass;
import Utils.APIPayloadConstant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class APIWorkFlowSteps {

    RequestSpecification request;
    Response response;
    public static String Employee_id;
    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {
     /*   request = given().header("Content-Type", "application/json").
                header("Authorization",GenerateTokenSteps.token).body("{\n" +
                        "  \"emp_firstname\": \"justin\",\n" +
                        "  \"emp_lastname\": \"azzuri\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2000-07-21\",\n" +
                        "  \"emp_status\": \"happy\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");*/
        request = given().header(APIConstantsClass.HEADER_CONTENT_TYPE_KEY, APIConstantsClass.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstantsClass.HEADER_AUTHORIZATION_KEY,GenerateTokenSteps.token).
                body(APIPayloadConstant.createEmployeePayload()/*"{\n" +
                        "  \"emp_firstname\": \"justin\",\n" +
                        "  \"emp_lastname\": \"azzuri\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2000-07-21\",\n" +
                        "  \"emp_status\": \"happy\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}*/);
    }
    @When("a post call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
         response = request.when().post(APIConstantsClass.CREATE_EMPLOYEE_URI);
         response.prettyPrint();
    }
    @Then("the status code for creating employee is {int}")
    public void the_status_code_for_creating_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee created contains key {string} and value {string}")
    public void the_employee_created_contains_key_and_value(String key, String value) {
       response.then().assertThat().body(key,equalTo(value));
    }
    @Then("the employee id {string} is stored as global variable")
    public void the_employee_id_is_stored_as_global_variable(String empId) {
        Employee_id=response.jsonPath().getString(empId);
        System.out.println(Employee_id);
    }

    @Given("a request is prepared for retrieving an employee")
    public void a_request_is_prepared_for_retrieving_an_employee() {
        request = given().header(APIConstantsClass.HEADER_AUTHORIZATION_KEY,GenerateTokenSteps.token)
                .queryParam("employee_id", Employee_id);
    }

    @When("a GET call is made to retrieve the employee")
    public void a_get_call_is_made_to_retrieve_the_employee() {
        response = request.when().get("/getOneEmployee.php");
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee id {string} must match with globally stored employee id")
    public void the_employee_id_must_match_with_globally_stored_employee_id(String empId) {
        String temporaryEmpid = response.jsonPath().getString(empId);
        Assert.assertEquals(Employee_id, temporaryEmpid);
    }

    @Then("this employee data at {string} object matches with the data used to create one employee")
    public void this_employee_data_at_object_matches_with_the_data_used_to_create_one_employee
            (String employeeObject, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        List<Map<String,String>> expectedData=dataTable.asMaps();
        // since we are calling the whole object sw use get
// this maop will return us the actual data that we have
        Map<String,String> actualData=  response.body().jsonPath().get(employeeObject);
        // for each loop
        for (Map<String,String>map:expectedData) {
            // to keep the order and to avoid duplicate to  get all keys
            Set<String> keys=map.keySet();
            //this loop will bring each key
            for (String key:keys) {
               // from the key we will get value
               String expectedValue=map.get(key) ;
               //
               String actualValue= actualData.get(key);
               Assert.assertEquals(expectedValue,actualValue);
            }
        }


    }

    @Given("a request is prepared for creating an employee using json payload")
    public void a_request_is_prepared_for_creating_an_employee_using_json_payload() {
        // Write code here that turns the phrase above into concrete actions
        request = given().header(APIConstantsClass.HEADER_CONTENT_TYPE_KEY,
                        APIConstantsClass.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstantsClass.HEADER_AUTHORIZATION_KEY, GenerateTokenSteps.token).
                body(APIPayloadConstant.createEmployeeJsonPayload());
    }
    @Given("a request is prepared for creating an employee with data {string} , {string} , {string} , {string} , {string} , {string} , {string}")
    public void a_request_is_prepared_for_creating_an_employee_with_data
            (String fn, String ln, String mn,
             String gender, String dob,
             String status, String jobTitle) {
        request = given().header(APIConstantsClass.HEADER_CONTENT_TYPE_KEY,
                        APIConstantsClass.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstantsClass.HEADER_AUTHORIZATION_KEY, GenerateTokenSteps.token).
                body(APIPayloadConstant.
                        createEmployeeJsonPayloadDynamic
                                (fn,ln,mn,gender,dob,status,jobTitle));
    }
}
