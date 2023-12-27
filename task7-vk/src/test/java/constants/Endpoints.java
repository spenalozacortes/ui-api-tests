package constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoints {
    public static final String CREATE_POST = "/wall.post";
    public static final String EDIT_POST = "/wall.edit";
    public static final String GET_UPLOAD_SERVER = "/photos.getWallUploadServer";
    public static final String SAVE_PHOTO = "/photos.saveWallPhoto";
}
