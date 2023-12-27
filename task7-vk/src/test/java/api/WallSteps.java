package api;

import constants.Endpoints;
import constants.Parameters;
import io.restassured.response.Response;

public class WallSteps extends BaseSteps {

    public Response createPost(String message) {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .queryParam(Parameters.MESSAGE, message)
                .when()
                .post(Endpoints.CREATE_POST);
    }

    public Response editPost(int id, String message, String attachment) {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .queryParam(Parameters.POST_ID, id)
                .queryParam(Parameters.MESSAGE, message)
                .queryParam(Parameters.ATTACHMENTS, attachment)
                .when()
                .post(Endpoints.EDIT_POST);
    }

    public Response addCommentToPost(int id, String comment) {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .queryParam(Parameters.POST_ID, id)
                .queryParam(Parameters.MESSAGE, comment)
                .when()
                .post(Endpoints.ADD_COMMENT);
    }

    public Response getLikesFromPost(int id) {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .queryParam(Parameters.TYPE, "post")
                .queryParam(Parameters.ITEM_ID, id)
                .when()
                .get(Endpoints.GET_LIKES);
    }

    public Response deletePost(int id) {
        return getBaseReq()
                .queryParam(Parameters.ACCESS_TOKEN, ACCESS_TOKEN)
                .queryParam(Parameters.VERSION, VERSION)
                .queryParam(Parameters.POST_ID, id)
                .when()
                .post(Endpoints.DELETE_POST);
    }
}
