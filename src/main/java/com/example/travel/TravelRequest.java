package com.example.travel;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TravelRequest {
    @NotBlank(message = "Title is empty")
    private String title;
    @NotBlank(message = "Description is empty")
    private String description;
    @NotBlank(message = "Photo url is empty")
    private String photoUrl;
}
