package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UploadServerResponse {
    @JsonProperty("album_id")
    private int albumId;
    @JsonProperty("upload_url")
    private String uploadUrl;
    @JsonProperty("user_id")
    private int userId;
}
