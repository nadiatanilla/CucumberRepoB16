package Utils;

import io.restassured.RestAssured;

public class APIConstantsClass {

    public static final String BaseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static final String CREATE_EMPLOYEE_URI = BaseURI+"/createEmployee.php";
    public static final String GET_ONE_EMPLOYEE_URI = BaseURI+"/getOneEmployee.php";
    public static final String UPDATE_EMPLOYEE_URI = BaseURI+"/updateEmployee.php";
    public static final String GENERATE_TOKEN_URI = BaseURI+"/generateToken.php";
    //similarly you can add endpoint for remainings

    //we need to add headers as well
    public static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
    public static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";

}
