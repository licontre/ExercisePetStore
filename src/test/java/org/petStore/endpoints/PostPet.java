package org.petStore.endpoints;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import java.util.*;

public class PostPet {
    private String baseURL = "https://petstore.swagger.io/v2";
    public Response response;

    public PostPet(String baseURL){
        this.baseURL = baseURL;
    }
    public Response getResponse(){
        return response;
    }

    public Response postAvailable(String petName){
        Map<String,Object> jsonAsMap = getAvailablePet(petName);
        return SerenityRest.given()
                .contentType("application/json")
                .content(jsonAsMap).log().body()
                .baseUri(baseURL)
                .basePath("/pet")
                .when().post();
    }

    public Map<String,Object> getAvailablePet(){
        int id = Math.abs(new Random().nextInt());
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", id);
        jsonAsMap.put("name", "Revit");
        jsonAsMap.put("status", "available");
        jsonAsMap.put("photoUrls", new ArrayList<>(Arrays.asList()));
        return jsonAsMap;
    }
    public Map<String,Object> getAvailablePet(String name){
        int id = Math.abs(new Random().nextInt());
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", id);
        jsonAsMap.put("name", name);
        jsonAsMap.put("status", "available");
        jsonAsMap.put("photoUrls", new ArrayList<>(Arrays.asList()));
        return jsonAsMap;
    }
}
