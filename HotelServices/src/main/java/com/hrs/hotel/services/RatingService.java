package com.hrs.hotel.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrs.hotel.dtos.Ratings;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@GetMapping("api/rating/hotel/{hotelId}")
	List<Ratings> getRatingsByHoteID(@PathVariable String hotelId);
}
