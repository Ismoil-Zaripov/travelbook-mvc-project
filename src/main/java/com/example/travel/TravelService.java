package com.example.travel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelService {
    private final TravelRepository travelRepository;

    public List<TravelResponse> findByTopFiveTravel() {
        return travelRepository.findByTopFiveTravel()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TravelResponse mapToResponse(TravelEntity e){
        return TravelResponse.builder()
                .id(e.getId())
                .title(e.getTitle())
                .description(e.getDescription())
                .photoUrl(e.getPhotoUrl())
                .createdDate(e.getCreatedDate())
                .build();
    }
}
