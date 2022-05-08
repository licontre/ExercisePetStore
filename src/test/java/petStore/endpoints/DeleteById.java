package petStore.endpoints;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class DeleteById {
    private String baseURL = "https://petstore.swagger.io/v2";
    public Response response;

    public DeleteById(String baseURL){
        this.baseURL = baseURL;
    }
    public Response getResponse(){
        return response;
    }
    @Step("Deletes by Id {0}")
    public Response deleteById(String id){
        return SerenityRest.given()
                .when()
                .delete(baseURL+"/pet/"+id);
    }
}
