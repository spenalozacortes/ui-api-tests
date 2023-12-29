package api;

import constants.Endpoints;
import constants.Parameters;
import io.restassured.http.ContentType;
import models.PhotoResponse;
import models.TransferFileResponse;
import models.UploadServerResponse;
import org.apache.http.HttpStatus;
import utils.JsonMapperUtils;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PhotoSteps extends BaseSteps {

    public UploadServerResponse getUploadServer() {
        String response = getBaseReq()
                .when()
                .get(Endpoints.GET_UPLOAD_SERVER)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();
        return JsonMapperUtils.deserialize(response, RESPONSE_PATH, UploadServerResponse.class);
    }

    public TransferFileResponse transferFile(String path) {
        String uploadUrl = getUploadServer().getUploadUrl();
        String response = given()
                .contentType(ContentType.MULTIPART)
                .multiPart(Parameters.PHOTO, new File(path))
                .when()
                .post(uploadUrl)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();
        return JsonMapperUtils.deserialize(response, TransferFileResponse.class);
    }

    public PhotoResponse saveFile(String path) {
        TransferFileResponse transferFileResponse = transferFile(path);
        String response = getBaseReqMultipart()
                .multiPart(Parameters.SERVER, transferFileResponse.getServer())
                .multiPart(Parameters.PHOTO, transferFileResponse.getPhoto())
                .multiPart(Parameters.HASH, transferFileResponse.getHash())
                .when()
                .post(Endpoints.SAVE_PHOTO)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();
        return JsonMapperUtils.deserialize(response, RESPONSE_PATH, PhotoResponse.class);
    }
}
