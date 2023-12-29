package constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoints {
    private static final String WALL = "/wall";
    private static final String LIKES = "/likes";
    private static final String PHOTOS = "/photos";
    public static final String CREATE_POST = String.format("%s.post", WALL);
    public static final String EDIT_POST = String.format("%s.edit", WALL);
    public static final String DELETE_POST = String.format("%s.delete", WALL);
    public static final String ADD_COMMENT = String.format("%s.createComment", WALL);
    public static final String IS_LIKED = String.format("%s.isLiked", LIKES);
    public static final String GET_UPLOAD_SERVER = String.format("%s.getWallUploadServer", PHOTOS);
    public static final String SAVE_PHOTO = String.format("%s.saveWallPhoto", PHOTOS);
}
