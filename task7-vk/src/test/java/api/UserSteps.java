package api;

import constants.Endpoints;
import constants.Parameters;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

public class UserSteps extends BaseSteps {

    public Response getUser() {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .when()
                .get(Endpoints.GET_USER)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
    }
}
