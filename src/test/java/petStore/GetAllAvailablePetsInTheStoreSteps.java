package petStore;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import petStore.endpoints.FindByStatus;
import java.util.HashMap;
import java.util.List;


public class GetAllAvailablePetsInTheStoreSteps {
    private final String baseURL = "https://petstore.swagger.io/v2";

    private FindByStatus findByStatus;
    private Response response;

    @Given("the user is on the website")
    public void theUserIsOnTheWebsite() {
        RestAssured.defaultParser = Parser.JSON;
        findByStatus = new FindByStatus(baseURL);
    }

    @When("retrieve all the available pets")
    public void retrieveAllTheAvailablePets() {
        response = findByStatus.findByStatus("available");
    }

    @Then("all the pets in the response must be available")
    public void allThePetsInTheResponseMustBeAvailable() {
        List<HashMap> jsonResponse = response.jsonPath().getList("$");
        for (HashMap elem : jsonResponse){
            Assert.assertEquals(elem.get("status"),"available");
        }
    }

}
