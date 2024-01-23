package models;

import lombok.Data;

@Data
public class TransferFileResponse {
    private int server;
    private String photo;
    private String hash;
}
