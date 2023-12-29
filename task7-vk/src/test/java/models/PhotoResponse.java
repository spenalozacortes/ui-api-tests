package models;

import lombok.Data;

import java.util.List;

@Data
public class PhotoResponse {
    private List<Photo> response;
}
