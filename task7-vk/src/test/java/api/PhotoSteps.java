package api;

import constants.Endpoints;
import constants.Parameters;
import models.PhotoResponse;
import models.TransferFileResponse;
import models.UploadServerResponse;
import org.apache.http.HttpStatus;

import java.io.File;

public class PhotoSteps extends BaseSteps {

    public UploadServerResponse getUploadServer() {
        return getBaseReq()
                .when()
                .get(Endpoints.GET_UPLOAD_SERVER)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .jsonPath()
                .getObject(RESPONSE_PATH, UploadServerResponse.class);
    }

    public TransferFileResponse transferFile(String path) {
        return getBaseMultipartReqSpec(Parameters.PHOTO, new File(path))
                .when()
                .post(getUploadServer().getUploadUrl())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .jsonPath()
                .getObject(ROOT_PATH, TransferFileResponse.class);
    }

    public PhotoResponse saveFile(String path) {
        TransferFileResponse transferFileResponse = transferFile(path);
        return getBaseReq()
                .queryParam(Parameters.SERVER, transferFileResponse.getServer())
                .queryParam(Parameters.PHOTO, transferFileResponse.getPhoto())
                .queryParam(Parameters.HASH, transferFileResponse.getHash())
                .when()
                .post(Endpoints.SAVE_PHOTO)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .jsonPath()
                .getList(RESPONSE_PATH, PhotoResponse.class).get(0);
    }
}
