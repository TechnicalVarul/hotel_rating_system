package com.hrs.user.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrs.user.dtos.Ratings;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@GetMapping("api/rating/user/{userId}")
	List<Ratings> getRatingsByUserID(@PathVariable String userId);
}
