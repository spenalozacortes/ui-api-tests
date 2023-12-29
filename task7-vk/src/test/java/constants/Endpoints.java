package constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoints {
    public static final String CREATE_POST = "/wall.post";
    public static final String EDIT_POST = "/wall.edit";
    public static final String DELETE_POST = "/wall.delete";
    public static final String ADD_COMMENT = "/wall.createComment";
    public static final String GET_LIKES = "/likes.getList";
    public static final String GET_UPLOAD_SERVER = "/photos.getWallUploadServer";
    public static final String SAVE_PHOTO = "/photos.saveWallPhoto";
}
