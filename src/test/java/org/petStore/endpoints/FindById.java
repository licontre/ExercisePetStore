package org.petStore.endpoints;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class FindById {
    private String baseURL = "https://petstore.swagger.io/v2";
    public Response response;

    public FindById(String baseURL){
        this.baseURL = baseURL;
    }
    public Response getResponse(){
        return response;
    }
    public Response findById(String id){
        return SerenityRest.given()
                .when()
                .get(baseURL+"/pet/"+id);
    }
}
