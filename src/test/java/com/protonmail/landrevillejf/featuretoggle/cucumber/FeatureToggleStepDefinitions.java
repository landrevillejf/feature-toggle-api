package com.protonmail.landrevillejf.featuretoggle.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeatureToggleStepDefinitions {


    private final TestRestTemplate restTemplate;
    private ResponseEntity<String> response;

    public FeatureToggleStepDefinitions(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Given("the application is running")
    public void theApplicationIsRunning() {
        // You can set up any necessary test context here
    }

    @When("I make a GET request to {string}")
    public void iMakeAGETRequestTo(String endpoint) {
        // Send a GET request to the specified endpoint
        response = restTemplate.getForEntity(endpoint, String.class);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCodeValue();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("the response should contain JSON")
    public void theResponseShouldContainJSON() {
        // Implement JSON validation logic here
    }
}

