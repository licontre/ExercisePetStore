package org.petStore.endpoints;

import com.google.gson.Gson;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import java.util.Map;

public class UpdatePet {
    private String baseURL = "https://petstore.swagger.io/v2";
    public Response response;

    public UpdatePet(String baseURL){
        this.baseURL = baseURL;
    }
    public Response getResponse(){
        return response;
    }
    public Response updatePet(Map petMap){
        return SerenityRest.given()
                .contentType("application/json")
                .content(petMap).log().body()
                .baseUri(baseURL)
                .basePath("/pet")
                .when().put();
    }
    public Response updatePet(String jsonString){
        Map jsonAsMap = (new Gson()).fromJson(jsonString, Map.class);
        return SerenityRest.given()
                .contentType("application/json")
                .content(jsonAsMap).log().body()
                .baseUri(baseURL)
                .basePath("/pet")
                .when().put();
    }

}
