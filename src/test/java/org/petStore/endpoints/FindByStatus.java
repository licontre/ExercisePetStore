package org.petStore.endpoints;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class FindByStatus {
    private String baseURL = "https://petstore.swagger.io/v2";
    public Response response;

    public FindByStatus(String baseURL){
        this.baseURL = baseURL;
    }
    public Response getResponse(){
        return response;
    }
    public Response findByStatus(String status){
        return SerenityRest.given()
                .queryParam("status",status)
                .when()
                .get(baseURL+"/pet/findByStatus");
    }
}
