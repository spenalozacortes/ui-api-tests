package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostResponse {
    @JsonProperty("post_id")
    private int postId;
}
