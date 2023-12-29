package api;

import constants.Endpoints;
import constants.Parameters;
import constants.Values;
import models.CommentResponse;
import models.DeleteResponse;
import models.LikesResponse;
import models.PostResponse;
import org.apache.http.HttpStatus;

public class WallSteps extends BaseSteps {

    public PostResponse createPost(String message) {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .queryParam(Parameters.MESSAGE, message)
                .when()
                .post(Endpoints.CREATE_POST)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(PostResponse.class);
    }

    public PostResponse editPost(int id, String message, String attachment) {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .queryParam(Parameters.POST_ID, id)
                .queryParam(Parameters.MESSAGE, message)
                .queryParam(Parameters.ATTACHMENTS, attachment)
                .when()
                .post(Endpoints.EDIT_POST)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(PostResponse.class);
    }

    public CommentResponse addCommentToPost(int id, String comment) {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .queryParam(Parameters.POST_ID, id)
                .queryParam(Parameters.MESSAGE, comment)
                .when()
                .post(Endpoints.ADD_COMMENT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(CommentResponse.class);
    }

    public LikesResponse getLikesFromPost(int id) {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .queryParam(Parameters.TYPE, Values.POST)
                .queryParam(Parameters.ITEM_ID, id)
                .when()
                .get(Endpoints.GET_LIKES)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(LikesResponse.class);
    }

    public DeleteResponse deletePost(int id) {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .queryParam(Parameters.POST_ID, id)
                .when()
                .post(Endpoints.DELETE_POST)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(DeleteResponse.class);
    }
}
