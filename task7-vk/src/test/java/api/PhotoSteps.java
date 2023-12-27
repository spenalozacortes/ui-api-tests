package api;

import constants.Endpoints;
import constants.Keys;
import constants.Parameters;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.JsonPathUtils;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PhotoSteps extends BaseSteps {

    public Response getUploadServer() {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .when()
                .get(Endpoints.GET_UPLOAD_SERVER);
    }

    public Response transferFile(String path) {
        Response uploadServerResponse = getUploadServer();
        String uploadUrl = JsonPathUtils.getValueFromResponseByKey(uploadServerResponse, Keys.UPLOAD_URL);
        return given()
                .contentType(ContentType.MULTIPART)
                .multiPart(Parameters.PHOTO, new File(path))
                .when()
                .post(uploadUrl);
    }

    public Response saveFile(String path) {
        Response transferFileResponse = transferFile(path);
        int server = JsonPathUtils.getValueFromResponseByKey(transferFileResponse, Keys.SERVER);
        String photo = JsonPathUtils.getValueFromResponseByKey(transferFileResponse, Keys.PHOTO);
        String hash = JsonPathUtils.getValueFromResponseByKey(transferFileResponse, Keys.HASH);
        return getBaseReq()
                .contentType(ContentType.MULTIPART)
                .multiPart(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .multiPart(Parameters.VERSION, VERSION)
                .multiPart(Parameters.SERVER, server)
                .multiPart(Parameters.PHOTO, photo)
                .multiPart(Parameters.HASH, hash)
                .when()
                .post(Endpoints.SAVE_PHOTO);
    }
}
