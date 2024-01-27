package com.hrs.rating.services;

import java.util.List;

import com.hrs.rating.dtos.RatingDTO;

public interface RatingService {
	
	RatingDTO createRating(RatingDTO rating);
	List<RatingDTO> getAllRatings();
	List<RatingDTO> getRatingsByHoteID(String hotelId);
	List<RatingDTO> getRatingsByUserID(String userId);

}
