package api;

import constants.Endpoints;
import constants.Parameters;
import io.restassured.response.Response;

public class PostSteps extends BaseSteps {

    public Response createPost(String message) {
        return getBaseReq()
                .queryParam(Parameters.MESSAGE, message)
                .when()
                .post(Endpoints.CREATE_POST);
    }
}
