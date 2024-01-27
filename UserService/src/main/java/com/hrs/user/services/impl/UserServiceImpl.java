package com.hrs.user.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hrs.user.dtos.Ratings;
import com.hrs.user.dtos.UserDTO;
import com.hrs.user.entities.User;
import com.hrs.user.exceptions.ResourceNotFoundException;
import com.hrs.user.repos.UserRepo;
import com.hrs.user.services.RatingService;
import com.hrs.user.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//@Autowired
	//private RestTemplate restTemplate;
	
	@Autowired
	private RatingService ratingService;
	
	@Override
	public UserDTO saveUser(UserDTO user) {
		
		User savedUser = userRepo.save(modelMapper.map(user, User.class));
		
		return modelMapper.map(savedUser, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserDTO> users = userRepo.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
		for(UserDTO user: users) {
			// USING REST TEMPLET
			//ArrayList<Ratings> ratings = restTemplate.getForObject("http://RATING-SERVICE/api/rating/user/"+user.getUserId(), ArrayList.class);
			List<Ratings> ratings = ratingService.getRatingsByUserID(user.getUserId());
			user.setRatings(ratings);
		}
		return users;
	}

	@Override
	public UserDTO getUserById(String userId) {
		
		UserDTO fetchedUser = modelMapper.map(userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("USER", "userId", userId)), UserDTO.class);
		// Calling RATING_SERVICE
		
		// USING REST TEMPLETS
		//ArrayList<Ratings> ratings = restTemplate.getForObject("http://RATING-SERVICE/api/rating/user/"+userId, ArrayList.class);	
		
		List<Ratings> ratings = ratingService.getRatingsByUserID(userId);
		
		fetchedUser.setRatings(ratings);
		
		return fetchedUser;
	}

	@Override
	public void deleteUser(String userId) {
		
		User userToDelete = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("USER", "userId", userId));
		userRepo.delete(userToDelete);
		
	}

	@Override
	public UserDTO updateUser(UserDTO user, String userId) {
		User userToUpdate = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("USER", "userId", userId));
		userToUpdate.setName(user.getName());
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setAbout(user.getAbout());
		
		return modelMapper.map(userRepo.save(userToUpdate), UserDTO.class);
	}

}
