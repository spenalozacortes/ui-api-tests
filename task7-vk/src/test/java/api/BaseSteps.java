package api;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import constants.Parameters;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class BaseSteps {

    protected RequestSpecification getBaseReq() {
        return RestAssured.given()
                .baseUri(EnvironmentConfig.getBaseUri())
                .queryParam(Parameters.ACCESS_TOKEN, CredentialsConfig.getAccessToken())
                .queryParam(Parameters.VERSION, 5.199);
    }
}
