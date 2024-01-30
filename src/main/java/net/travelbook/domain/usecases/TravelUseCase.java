package net.travelbook.domain.usecases;

import java.util.List;

public interface TravelUseCase <REQUEST, RESPONSE> {
    List<RESPONSE> findByTopFiveTravelPosts();
    List<RESPONSE> getAllTravelPosts();
    void saveTravelPost(REQUEST travelPostRequest);
    List<RESPONSE> searchTravelPost(String query);
    void deleteTravelPost(Integer travelPostId);
    RESPONSE getTravelPostById(Integer travelPostId);
    boolean updateTravelPost(Integer id, REQUEST travelPostRequest);
}
