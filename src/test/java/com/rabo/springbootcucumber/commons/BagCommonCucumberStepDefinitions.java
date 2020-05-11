package com.rabo.springbootcucumber.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Given;

public class BagCommonCucumberStepDefinitions {

	@Given("^the bag is empty$")
	public void the_bag_is_empty() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8082/api/employee/",
				String.class);
		System.out.println("response is xxxxx" + response);
	}

}
