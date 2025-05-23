package stepdefinitions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.Map;

public class PetSteps {

    private Response response;
    private Map<String, Object> petPayload;

    @Given("the pet payload with id {int}, name {string}, and status {string}")
    public void createValidPetPayload(int id, String name, String status) {
        petPayload = new HashMap<>();
        petPayload.put("id", id);
        petPayload.put("name", name);
        petPayload.put("status", status);
    }

    @Given("the pet payload with id {int} and missing name field")
    public void createInvalidPetPayload(int id) {
        petPayload = new HashMap<>();
        petPayload.put("id", id);
        // name is intentionally missing to trigger negative test
    }

    @When("I send a POST request to {string}")
    public void sendPostRequest(String endpoint) {
        response = SerenityRest.given()
            .header("Content-Type", "application/json")
            .body(petPayload)
            .when()
            .post("https://petstore.swagger.io/v2" + endpoint);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the response should contain the pet with name {string}")
    public void verifyPetNameInResponse(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(expectedName, actualName);
    }
}

