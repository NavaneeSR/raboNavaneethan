package com.rabo.springbootcucumber.basics;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberStepDefinitions {

    private final Logger log = LoggerFactory.getLogger(CucumberStepDefinitions.class);
    private RequestSpecification requestSpecification;
    private Response response;
    @Autowired

    @Given("the user accessing base URI")
    public void the_user_accessing_base_URI() {
    	requestSpecification = RestAssured.with();
    	requestSpecification.baseUri("http://localhost:8082");
    			
    }

    @When("the user send a get request {string}")
    public void the_user_send_a_get_request(String resource) {
    	System.out.println("When xxxxx");
    	Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
    	response = requestSpecification.headers(headers).get(resource);
    	System.out.println("mock response  xxxxx"+response.prettyPrint());
    }

    @Then("the user should see the response code as {int}")
    public void the_user_should_see_the_response_code_as(Integer int1) {
    	System.out.println("response code xxxxx"+response.getStatusCode());
    	Assert.assertEquals(200, response.getStatusCode());
    }

}
