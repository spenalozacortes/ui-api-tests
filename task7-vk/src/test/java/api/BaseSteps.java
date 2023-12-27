package api;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class BaseSteps {

    protected static final String ACCESS_TOKEN = CredentialsConfig.getAccessToken();
    protected static final double VERSION = 5.199;

    protected RequestSpecification getBaseReq() {
        return RestAssured.given()
                .baseUri(EnvironmentConfig.getBaseUri());
    }
}
