package petStore.endpoints;

import com.google.gson.Gson;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

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
    @Step("Update pet")
    public Response updatePet(Map petMap){
        String body = (new Gson()).toJson(petMap);
        return SerenityRest.given()
                .contentType("application/json")
                .body(body)
                //.content(petMap).log().body()
                .baseUri(baseURL)
                .basePath("/pet")
                .when().put();
    }
    @Step("Update pet by json")
    public Response updatePet(String jsonString){
        Map jsonAsMap = (new Gson()).fromJson(jsonString, Map.class);
        String body = (new Gson()).toJson(jsonAsMap);
        return SerenityRest.given()
                .contentType("application/json")
                .body(body)
//                .content(jsonAsMap).log().body()
                .baseUri(baseURL)
                .basePath("/pet")
                .when().put();
    }

}
