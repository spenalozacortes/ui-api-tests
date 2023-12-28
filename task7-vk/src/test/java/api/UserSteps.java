package api;

import constants.Endpoints;
import constants.Parameters;
import io.restassured.response.Response;

public class UserSteps extends BaseSteps {

    public Response getUser() {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .when()
                .get(Endpoints.GET_USER);
    }
}
