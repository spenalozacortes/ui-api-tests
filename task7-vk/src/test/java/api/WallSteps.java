package api;

import constants.Endpoints;
import constants.Parameters;
import constants.ObjectType;
import models.CommentResponse;
import models.DeleteResponse;
import models.LikeResponse;
import models.PostResponse;
import org.apache.http.HttpStatus;
import utils.JsonMapperUtils;

public class WallSteps extends BaseSteps {

    public PostResponse createPost(String message) {
        String response = getBaseReq()
                .queryParam(Parameters.MESSAGE, message)
                .when()
                .post(Endpoints.CREATE_POST)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();
        return JsonMapperUtils.deserialize(response, RESPONSE_PATH, PostResponse.class);
    }

    public PostResponse editPost(int id, String message, String attachment) {
        String response = getBaseReq()
                .queryParam(Parameters.POST_ID, id)
                .queryParam(Parameters.MESSAGE, message)
                .queryParam(Parameters.ATTACHMENTS, attachment)
                .when()
                .post(Endpoints.EDIT_POST)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();
        return JsonMapperUtils.deserialize(response, RESPONSE_PATH, PostResponse.class);
    }

    public DeleteResponse deletePost(int id) {
        return getBaseReq()
                .queryParam(Parameters.POST_ID, id)
                .when()
                .post(Endpoints.DELETE_POST)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().as(DeleteResponse.class);
    }

    public CommentResponse addCommentToPost(int id, String comment) {
        String response = getBaseReq()
                .queryParam(Parameters.POST_ID, id)
                .queryParam(Parameters.MESSAGE, comment)
                .when()
                .post(Endpoints.ADD_COMMENT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();
        return JsonMapperUtils.deserialize(response, RESPONSE_PATH, CommentResponse.class);
    }

    public LikeResponse getIsLiked(int postId, int userId) {
        String response = getBaseReq()
                .queryParam(Parameters.USER_ID, userId)
                .queryParam(Parameters.TYPE, ObjectType.POST)
                .queryParam(Parameters.ITEM_ID, postId)
                .when()
                .get(Endpoints.IS_LIKED)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();
        return JsonMapperUtils.deserialize(response, RESPONSE_PATH, LikeResponse.class);
    }
}
