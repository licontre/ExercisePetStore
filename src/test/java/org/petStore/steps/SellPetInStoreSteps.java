package org.petStore.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.petStore.endpoints.*;

import java.util.Map;

public class SellPetInStoreSteps {
    private final String baseURL = "https://petstore.swagger.io/v2";
    private FindByStatus findByStatus;
    private FindById findById;
    private UpdatePet updatePet;
    private DeleteById deleteById;
    private PostPet postPet;
    private String petName;
    private Map<String,Object> petObject;

    @Given("The user has put his pet (.*) available on the application")
    public void theUserHasPutHisPetAvailableOnTheApplication(String petName) {
        this.petName = petName;
        postPet = new PostPet(baseURL);
        findByStatus = new FindByStatus(baseURL);
        findById = new FindById(baseURL);
        updatePet = new UpdatePet(baseURL);
        deleteById = new DeleteById(baseURL);
        Response responsePost = postPet.postAvailable(petName);
        petObject = responsePost.jsonPath().getMap("$");
        String statusString = (String) petObject.get("status");
        Assert.assertEquals(statusString,"available");

    }
    @And("checks that the pet is available")
    public void checksThatThePetIsAvailable(){
        String petId = Integer.toString((Integer) petObject.get("id")) ;
        Response responseGet = findById.findById(petId);
        Map<String,Object> jsonMap = responseGet.jsonPath().getMap("$");
        Assert.assertEquals(jsonMap.get("status"),"available");
    }

    @When("The pet is sold")
    public void thePetIsSold(){
        petObject.put("status","sold");
        Response responseUpdate = updatePet.updatePet(petObject);
        Assert.assertEquals(responseUpdate.getStatusCode(),200);
    }

    @Then("The pet is in the application with status sold")
    public void thePetIsInTheApplicationWithStatusSold() {
        String petId = Integer.toString((Integer) petObject.get("id")) ;
        Response responseGet = findById.findById(petId);
        Map<String,Object> jsonMap = responseGet.jsonPath().getMap("$");
        Assert.assertEquals(jsonMap.get("status"),"sold");
    }
    @When("the user removes the post on the application")
    public void theUserRemovesThePostOnTheApplication(){
        String petId = Integer.toString((Integer) petObject.get("id")) ;
        Response responseDelete =deleteById.deleteById(petId);
        Assert.assertEquals(responseDelete.getStatusCode(),200);
    }
    @Then("the pet is not in the application")
    public void thePetIsNotInTheApplication(){
        String petId = Integer.toString((Integer) petObject.get("id")) ;
        Response responseGet = findById.findById(petId);
        Map<String,Object> jsonMap = responseGet.jsonPath().getMap("$");
        Assert.assertEquals(jsonMap.get("type"),"error");
        Assert.assertEquals(jsonMap.get("message"),"Pet not found");
    }


}
