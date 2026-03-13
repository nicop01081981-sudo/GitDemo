package steps;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.Color;
import models.ColorList;
import models.LoginRequest;
import models.LoginResponse;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ReqResSteps extends BaseTest {
	
    LoginResponse loginResponse;

    @Given("the user prepares login request")
    public void the_user_prepares_login_request() { }

    @When("the user logs in with email {string} and password {string}")
    public void the_user_logs_in_with_email_and_password(String email, String password) throws FileNotFoundException {
        LoginRequest login = new LoginRequest();
        login.setEmail(email);
        login.setPassword(password);

        BaseTest.response = io.restassured.RestAssured
                .given()
                .spec(requestSpecification())
                .body(login)
                .post("/api/login");

        System.out.println(BaseTest.response.asString()); // va afișa tokenul sau eroarea
        System.out.println("test1");
        System.out.println("test2");
        System.out.println("test3");
        if (BaseTest.response.statusCode() == 200) {
            loginResponse = BaseTest.response.as(LoginResponse.class);
        }
    }
    
    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
    	BaseTest.response.then().statusCode(statusCode);
    }
    @Then("the login response status should be {int}")
    public void the_login_response_status_should_be(int statusCode) {
    	BaseTest.response.then().statusCode(statusCode);
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain(String message) {
        assertTrue(BaseTest.response.asString().contains(message));
    }

    @When("the user requests colors")
    public void the_user_requests_colors() throws FileNotFoundException {
    	BaseTest.response = io.restassured.RestAssured
                .given()
                .spec(requestSpecification())
                .get("/api/unknown");
        System.out.println(BaseTest.response.asString());
    }

    @Then("the response should contain {int} colors")
    public void the_response_should_contain_colors(int count) {
        ColorList colors = BaseTest.response.as(ColorList.class);
        assertEquals(count, colors.getData().size());
    }

    @Then("the first color name should be {string}")
    public void the_first_color_name_should_be(String name) {
        ColorList colors = BaseTest.response.as(ColorList.class);
        assertEquals(name, colors.getData().get(0).getName());
    }

    @Then("the color {string} should have year {int}")
    public void the_color_should_have_year(String colorName, int year) {
        ColorList colors = BaseTest.response.as(ColorList.class);
        for (Color c : colors.getData()) {
            if (c.getName().equals(colorName)) {
                assertEquals(year, c.getYear());
            }
        }
    }

}
