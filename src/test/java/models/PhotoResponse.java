package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PhotoResponse {
    @JsonProperty("album_id")
    private int albumId;
    private int date;
    private int id;
    @JsonProperty("owner_id")
    private int ownerId;
    @JsonProperty("access_key")
    private String accessKey;
    private List<Size> sizes;
    private String text;
    @JsonProperty("web_view_token")
    private String webViewToken;
    @JsonProperty("has_tags")
    private boolean hasTags;
}
