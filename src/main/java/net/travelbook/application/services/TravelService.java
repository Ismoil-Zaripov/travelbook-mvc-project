package net.travelbook.application.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.travelbook.domain.entities.TravelEntity;
import net.travelbook.domain.models.request.TravelRequest;
import net.travelbook.domain.models.response.TravelResponse;
import net.travelbook.domain.usecases.TravelUseCase;
import net.travelbook.infrastructure.jpa.repository.TravelJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelService implements TravelUseCase<TravelRequest, TravelResponse> {

    private final TravelJpaRepository travelRepository;

    @Override
    public List<TravelResponse> findByTopFiveTravelPosts() {
        return travelRepository
                .findByTopFiveTravelPosts()
                .stream()
                .map(mapToTravelResponse())
                .toList();
    }

    @Override
    public List<TravelResponse> getAllTravelPosts() {
        return travelRepository
                .findAll()
                .stream()
                .map(mapToTravelResponse())
                .toList();
    }

    @Override
    public void saveTravelPost(TravelRequest travelPostRequest) {

        var travel = TravelEntity.builder()
                .title(travelPostRequest.title())
                .description(travelPostRequest.description())
                .photoUrl(travelPostRequest.photoUrl())
                .build();

        travelRepository.save(travel);
    }

    @Override
    public List<TravelResponse> searchTravelPost(String query) {
        return travelRepository
                .searchTravelPost(query)
                .stream()
                .map(mapToTravelResponse())
                .toList();
    }

    private Function<TravelEntity, TravelResponse> mapToTravelResponse() {
        return travelEntity -> TravelResponse.builder()
                .id(travelEntity.getId())
                .title(travelEntity.getTitle())
                .description(travelEntity.getDescription())
                .photoUrl(travelEntity.getPhotoUrl())
                .createdDate(travelEntity.getCreatedDate())
                .build();
    }

    @Override
    public void deleteTravelPost(Integer travelPostId) {
        var travel = travelRepository.findById(travelPostId).orElseThrow();
        travelRepository.delete(travel);
    }

    @Override
    public TravelResponse getTravelPostById(Integer travelPostId) {
        return travelRepository
                .findById(travelPostId)
                .map(mapToTravelResponse())
                .orElseThrow();
    }

    @Override
    public boolean updateTravelPost(
            Integer travelId,
            TravelRequest travelPostRequest
    )
    {
        var optionalTravel = travelRepository.findById(travelId);
        if (optionalTravel.isEmpty()) return false;

        var travelEntity = optionalTravel.get();

        travelEntity.setTitle(travelPostRequest.title());
        travelEntity.setDescription(travelPostRequest.description());
        travelEntity.setPhotoUrl(travelPostRequest.photoUrl());

        travelRepository.save(travelEntity);
        return true;
    }
}
