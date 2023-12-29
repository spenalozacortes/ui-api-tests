package api;

import constants.Endpoints;
import constants.Keys;
import constants.Parameters;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.PhotoResponse;
import models.UploadServerResponse;
import org.apache.http.HttpStatus;
import utils.ResponseUtils;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PhotoSteps extends BaseSteps {

    public UploadServerResponse getUploadServer() {
        return getBaseReq()
                .when()
                .get(Endpoints.GET_UPLOAD_SERVER)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(UploadServerResponse.class);
    }

    public Response transferFile(String path) {
        String uploadUrl = getUploadServer().getResponse().getUpload_url();
        return given()
                .contentType(ContentType.MULTIPART)
                .multiPart(Parameters.PHOTO, new File(path))
                .when()
                .post(uploadUrl)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
    }

    public PhotoResponse saveFile(String path) {
        Response transferFileResponse = transferFile(path);
        int server = ResponseUtils.getValueFromResponseByKey(transferFileResponse, Keys.SERVER);
        String photo = ResponseUtils.getValueFromResponseByKey(transferFileResponse, Keys.PHOTO);
        String hash = ResponseUtils.getValueFromResponseByKey(transferFileResponse, Keys.HASH);
        return getBaseReqMultipart()
                .multiPart(Parameters.SERVER, server)
                .multiPart(Parameters.PHOTO, photo)
                .multiPart(Parameters.HASH, hash)
                .when()
                .post(Endpoints.SAVE_PHOTO)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(PhotoResponse.class);
    }
}
