package api;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import constants.Parameters;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.File;

public abstract class BaseSteps {

    private static final String API_URI = EnvironmentConfig.getApiUri();
    private static final String ACCESS_TOKEN = CredentialsConfig.getAccessToken();
    private static final String VERSION = EnvironmentConfig.getVersion();
    protected static final String RESPONSE_PATH = "response";
    protected static final String ROOT_PATH = ".";

    protected RequestSpecification getBaseReq() {
        return RestAssured.given()
                .baseUri(API_URI)
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION);
    }

    protected RequestSpecification getBaseMultipartReqSpec(String param, File paramSource) {
        return RestAssured.given()
                .baseUri(API_URI)
                .contentType(ContentType.MULTIPART)
                .multiPart(param, paramSource);
    }
}
