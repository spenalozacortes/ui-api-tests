package api;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class BaseSteps {

    protected static final String ACCESS_TOKEN = CredentialsConfig.getAccessToken();
    protected static final String VERSION = EnvironmentConfig.getVersion();

    protected RequestSpecification getBaseReq() {
        return RestAssured.given()
                .baseUri(EnvironmentConfig.getApiUri());
    }
}
