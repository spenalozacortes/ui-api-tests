package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CommentResponse {
    @JsonProperty("comment_id")
    private int commentId;
    @JsonProperty("parents_stack")
    private List<Integer> parentsStack;
}
