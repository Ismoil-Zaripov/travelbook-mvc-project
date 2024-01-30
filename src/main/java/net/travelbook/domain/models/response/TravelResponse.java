package net.travelbook.domain.models.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TravelResponse(
        Integer id,
        String title,
        String description,
        String photoUrl,
        LocalDateTime createdDate
) {}
