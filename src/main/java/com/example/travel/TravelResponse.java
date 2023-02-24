package com.example.travel;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TravelResponse {
    private Integer id;
    private String title;
    private String description;
    private String photoUrl;
    private LocalDateTime createdDate;
}
