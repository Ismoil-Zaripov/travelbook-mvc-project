package net.travelbook.domain.models.request;

import lombok.Builder;

@Builder
public record TravelRequest(
        String title,
        String description,
        String photoUrl
) {
}
