package com.hrs.user.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.user.dtos.UserDTO;
import com.hrs.user.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("api/user")
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
		return new ResponseEntity<>(userService.saveUser(user),HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "hotelToRatingBreaker", fallbackMethod = "hotelToRatingFallbackMethod")
	//@Retry(name = "hotelToRatingRetry",fallbackMethod = "hotelToRatingFallbackMethod")
	@RateLimiter(name ="hotelRateLimiter" ,fallbackMethod = "hotelToRatingFallbackMethod")
	public ResponseEntity<UserDTO> getUserById(@PathVariable String userId){
		//int retrycount = 0;
		//log.info("Retry Count : {}",retrycount);
		//retrycount++;
		return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.FOUND);
	}
	
	public ResponseEntity<UserDTO> hotelToRatingFallbackMethod(String userId,Exception ex){
		
		log.info("Fallback Method is called becaues some services are down : {}",ex.getMessage());
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setName("Dummy");
		user.setAbout("This user is created becaues some services are down.");
		user.setEmail("dummy@gmail.com");
		
		return new ResponseEntity<>(user,HttpStatus.OK);	
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUser(){
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.FOUND);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable String userId){
		userService.deleteUser(userId);
		return new ResponseEntity<>("User Deleted Succesfully",HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable String userId,@RequestBody UserDTO user){
		return new ResponseEntity<>(userService.updateUser(user, userId),HttpStatus.OK);
	}
}
