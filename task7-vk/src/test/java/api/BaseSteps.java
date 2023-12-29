package api;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import constants.Parameters;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseSteps {

    private static final String API_URI = EnvironmentConfig.getApiUri();
    private static final String ACCESS_TOKEN = CredentialsConfig.getAccessToken();
    private static final String VERSION = EnvironmentConfig.getVersion();

    protected RequestSpecification getBaseReq() {
        return RestAssured.given()
                .baseUri(API_URI)
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION);
    }

    protected RequestSpecification getBaseReqMultipart() {
        return RestAssured.given()
                .baseUri(API_URI)
                .contentType(ContentType.MULTIPART)
                .multiPart(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .multiPart(Parameters.VERSION, VERSION);
    }
}
