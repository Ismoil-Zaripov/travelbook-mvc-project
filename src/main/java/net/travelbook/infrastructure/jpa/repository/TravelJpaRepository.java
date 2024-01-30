package net.travelbook.infrastructure.jpa.repository;

import net.travelbook.domain.entities.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelJpaRepository extends JpaRepository<TravelEntity, Integer> {

//    @Query("select u from User u where lower(u.name) like lower(concat('%', :nameToFind,'%'))")
//    public List<User> findByNameFree(@Param("nameToFind") String name);

    @Query("FROM TravelEntity AS t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<TravelEntity> searchTravelPost(String title);
    @Query("FROM TravelEntity AS t ORDER BY t.createdDate DESC LIMIT 5")
    List<TravelEntity> findByTopFiveTravelPosts();

}
