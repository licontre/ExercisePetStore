package petStore.endpoints;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

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

    @Step("Post a Pet by name {0}")
    public Response postAvailable(String petName){
        Map<String,Object> jsonAsMap = getAvailablePet(petName);
        String body = (new Gson()).toJson(jsonAsMap);
        Response res = SerenityRest.given()
                .contentType("application/json")
                .body(body)
                //.content(jsonAsMap).log().body()
                .baseUri(baseURL)
                .basePath("/pet")
                .when().post();

        return  res;
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
