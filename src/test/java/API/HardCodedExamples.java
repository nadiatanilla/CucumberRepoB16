package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.devtools.v109.fetch.model.AuthChallengeResponse;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardCodedExamples {
// base URI=bASE URL+ END POINT
    // given =praparation
    // when hitting the endpoint
    // base url=base uri
    // prepare the requestr
    // http protocole added
    // TO FOLLOW THE REST ASSURED WE DID THIS
    //baseURI = baseURL + endpoint
    //given - preparation
    //when - hitting the endpoint
    //base URI = base URL

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    // value of token should be same as postman
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTA5MzIzNzEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5MDk3NTU3MSwidXNlcklkIjoiNTYxNSJ9.xQAoWkfKQTzhnTUSOwi-f0WLMZxmxusDR_yjUHw8E8s";
    //
    static String employee_id;

    //in this method we are going to create an employee
    //we need headers, body to prepare the request
    @Test
    public void acreateEmployee() {
        //preparing the request
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"justin\",\n" +
                        "  \"emp_lastname\": \"azzuri\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2000-07-21\",\n" +
                        "  \"emp_status\": \"happy\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");
        //hitting the endpoint
        Response response = request.when().post("/createEmployee.php");
        //verifying the response
        response.then().assertThat().statusCode(201);
        //  System.out.println(response);
        //this method we use to print the response of API in console
        response.prettyPrint();
        // verify all the values and response one by one
        // look at the import above hamcrest import
        response.then().assertThat().body("Employee.emp_firstname", equalTo("justin"));
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("ms"));

        // headers
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().header("X-Powered-By", "PHP/7.2.18");
        // it will return the empid and save it
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);


    }

    @Test
    public void bgetCreatedEmployee() {
        RequestSpecification request = given().header("Authorization", token).queryParam("employee_id", employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        // JSON PATH TO EXTRACT THE VALUE FROM THE RESPONSE
        String tempEmpId = response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id, tempEmpId);
    }


    @Test
    public void cUpdateEmployee() {
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"employee_id\": \"" + employee_id + "\",\n" +
                        "  \"emp_firstname\": \"Anna\",\n" +
                        "  \"emp_lastname\": \"Loa\",\n" +
                        "  \"emp_middle_name\": \"K\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2000-09-29\",\n" +
                        "  \"emp_status\": \"TESTER\",\n" +
                        "  \"emp_job_title\": \"TESTER\"\n" +
                        "}");
        Response response = request.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
    }

    @Test
    public void dgetUpdatedEmployee() {
        RequestSpecification request = given().header("Authorization", token).queryParam("employee_id", employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

        //  String tempEmpId=response.jsonPath().getString("employee.employee_id");
        //  Assert.assertEquals(employee_id,tempEmpId);
    }

    @Test
    public void ePartialEmployeeUpdated() {
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"employee_id\": \"" + employee_id + "\",\n" +
                        "  \"emp_middle_name\": \"LAL\"\n" +
                        "  }\n");
        Response response = request.when().patch("/updatePartialEmplyeesDetails.php");
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Message", equalTo("Employee record updated successfully"));
    }

    @Test
    public void fGetPartialUpdatedEmployee() {
        RequestSpecification request = given().header("Authorization", token).queryParam("employee_id", employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        String tempEmpId=response.jsonPath().getString("employee.employee_id");
          Assert.assertEquals(employee_id,tempEmpId);
    }
}