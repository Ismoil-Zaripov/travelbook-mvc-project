package com.example.travel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void save(TravelRequest request) {
        var travel = TravelEntity.builder()
                .title(request.getTitle())
                .photoUrl(request.getPhotoUrl())
                .description(request.getDescription())
                .createdDate(LocalDateTime.now())
                .build();

        travelRepository.save(travel);
    }

    public List<TravelResponse> search(String query) {
        return travelRepository.searchByTitle(query)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void delete(Integer id) {
        travelRepository.deleteById(id);
    }

    public TravelResponse getById(Integer id) {
        var travel = travelRepository.findById(id).get();
        return mapToResponse(travel);
    }

    public boolean update(Integer id, TravelResponse request) {
        var optional = travelRepository.findById(id);
        if (optional.isEmpty()) return false;

        var travel = optional.get();

        travel.setTitle(request.getTitle());
        travel.setDescription(request.getDescription());
        travel.setPhotoUrl(request.getPhotoUrl());

        travelRepository.save(travel);
        return true;
    }
}
