package stepdefs;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import static org.junit.Assert.*;

public class McpMultiplicationSteps {
    private String baseUrl;
    private Response response;
    private RequestSpecification request;

    @Given("the MCP server is running at {string}")
    public void theMcpServerIsRunningAt(String url) {
        this.baseUrl = url;
        RestAssured.baseURI = url;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }

    @When("I send a multiplication request with a={int} and b={int}")
    public void iSendAMultiplicationRequestWithAAndB(Integer a, Integer b) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("a", a);
        requestParams.put("b", b);
        
        response = request
            .body(requestParams.toString())
            .post("/dev/mcp/multiple");
    }

    @Then("I should receive a successful response")
    public void iShouldReceiveASuccessfulResponse() {
        assertEquals(200, response.getStatusCode());
    }

    @And("the result should be {int}")
    public void theResultShouldBe(Integer expectedResult) {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        assertEquals(expectedResult, jsonResponse.getInt("result"));
    }

    @When("I send a request to the home endpoint")
    public void iSendARequestToTheHomeEndpoint() {
        response = request.get("/");
    }

    @And("the message should be {string}")
    public void theMessageShouldBe(String expectedMessage) {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        assertEquals(expectedMessage, jsonResponse.getString("message"));
    }

    @When("I send a multiplication request with invalid input types")
    public void iSendAMultiplicationRequestWithInvalidInputTypes() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("a", "invalid");
        requestParams.put("b", "invalid");
        
        response = request
            .body(requestParams.toString())
            .post("/dev/mcp/multiple");
    }

    @When("I send a multiplication request without parameters")
    public void iSendAMultiplicationRequestWithoutParameters() {
        response = request
            .body("{}")
            .post("/dev/mcp/multiple");
    }

    @Then("I should receive an error response")
    public void iShouldReceiveAnErrorResponse() {
        assertTrue(response.getStatusCode() >= 400);
    }
}
