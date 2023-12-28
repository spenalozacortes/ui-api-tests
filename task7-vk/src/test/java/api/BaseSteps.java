package api;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import config.TestDataConfig;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class BaseSteps {

    protected static final String ACCESS_TOKEN = CredentialsConfig.getAccessToken();
    protected static final String VERSION = TestDataConfig.getVersion();

    protected RequestSpecification getBaseReq() {
        return RestAssured.given()
                .baseUri(EnvironmentConfig.getBaseUri());
    }
}
