package com.example.videostreamcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "videos")
public class Video  implements Serializable {
    @Id
    private String id;
    private String filename;
    private String status;
    private String mode;
    private Date convDate;
}
