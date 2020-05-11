package com.rabo.springbootcucumber.basics;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.springframework.http.HttpStatus;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	static WireMockServer wireMockServer;
	@Before
	public static void stubSetup() {
		wireMockServer = new WireMockServer(wireMockConfig().port(8082)); 
		wireMockServer.start();
		WireMock.configureFor("localhost", 8082);
		
		stubFor(get(urlEqualTo("/api/employee/")).willReturn(aResponse().withStatus(HttpStatus.OK.value())
				.withHeader("Content-Type", "application/json").withBodyFile("employee-array-req.json")));
		
		stubFor(get(urlEqualTo("/api/createEmployee/")).willReturn(aResponse().withStatus(HttpStatus.OK.value())
				.withHeader("Content-Type", "application/json").withBodyFile("employee-response.json")));

	}
	
	@After
	public void closeWire() {
		wireMockServer.stop();
	}
}
