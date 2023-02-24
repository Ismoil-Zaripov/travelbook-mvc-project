package com.example.travel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<TravelEntity, Integer> {
    @Query("FROM TravelEntity AS t WHERE t.title LIKE CONCAT('%',:title,'%') ORDER BY t.createdDate DESC ")
    List<TravelEntity> searchByTitle(String title); // HQL QUERY
}
