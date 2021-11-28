package com.example.videostreamconvworker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomMessage {
    private String id;
    private String filename;
    private String status;
    private String mode;
    private Date convDate;
}