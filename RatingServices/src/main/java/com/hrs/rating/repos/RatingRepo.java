package com.hrs.rating.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.rating.entities.Rating;

public interface RatingRepo extends JpaRepository<Rating, String> {

	List<Rating> findByHotelId(String hotelId);

	List<Rating> findByUserId(String userId);

}
