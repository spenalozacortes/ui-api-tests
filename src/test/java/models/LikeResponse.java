package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LikeResponse {
    private int liked;
    private int copied;
    @JsonProperty("reaction_id")
    private int reactionId;
}
