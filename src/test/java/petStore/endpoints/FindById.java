package petStore.endpoints;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class FindById {
    private String baseURL = "https://petstore.swagger.io/v2";
    public Response response;

    public FindById(String baseURL){
        this.baseURL = baseURL;
    }
    public Response getResponse(){
        return response;
    }
    @Step("Finds by Id {0}")
    public Response findById(String id){
        return SerenityRest.given()
                .when()
                .get(baseURL+"/pet/"+id);
    }
}
