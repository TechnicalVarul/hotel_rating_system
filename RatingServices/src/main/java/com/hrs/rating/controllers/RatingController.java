package com.hrs.rating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.rating.dtos.RatingDTO;
import com.hrs.rating.services.RatingService;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/")
	public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO rating){
	
		return new ResponseEntity<>(ratingService.createRating(rating),HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<RatingDTO>> getAllRatings(){
		return new ResponseEntity<>(ratingService.getAllRatings(),HttpStatus.OK);
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<RatingDTO>> getAllRatingsByHotelId(@PathVariable String hotelId){
		return new ResponseEntity<>(ratingService.getRatingsByHoteID(hotelId),HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<RatingDTO>> getAllRatingsByUserId(@PathVariable String userId){
		return new ResponseEntity<>(ratingService.getRatingsByUserID(userId),HttpStatus.OK);
	}
}
