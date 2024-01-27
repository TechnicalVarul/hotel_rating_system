package com.hrs.rating.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.rating.dtos.RatingDTO;
import com.hrs.rating.entities.Rating;
import com.hrs.rating.repos.RatingRepo;
import com.hrs.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired 
	private RatingRepo ratingRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RatingDTO createRating(RatingDTO rating) {
		Rating savedRating = ratingRepo.save(modelMapper.map(rating, Rating.class));
		return modelMapper.map(savedRating, RatingDTO.class);
	}

	@Override
	public List<RatingDTO> getAllRatings() {
		
		return ratingRepo.findAll().stream().map(e->modelMapper.map(e, RatingDTO.class)).toList();
	}

	@Override
	public List<RatingDTO> getRatingsByHoteID(String hotelId) {
		return ratingRepo.findByHotelId(hotelId).stream().map(e->modelMapper.map(e, RatingDTO.class)).toList();
	}

	@Override
	public List<RatingDTO> getRatingsByUserID(String userId) {
		return ratingRepo.findByUserId(userId).stream().map(e->modelMapper.map(e, RatingDTO.class)).toList();
	}

}
