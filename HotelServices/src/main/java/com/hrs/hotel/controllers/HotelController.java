package com.hrs.hotel.controllers;

import java.util.List;

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

import com.hrs.hotel.dtos.HotelDTO;
import com.hrs.hotel.services.HotelService;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/")
	public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotel){
		return new ResponseEntity<>(hotelService.createHotel(hotel),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<HotelDTO>> getAllHotels(){
		return new ResponseEntity<>(hotelService.getAllHotels(),HttpStatus.FOUND);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<HotelDTO> getHotelById(@PathVariable String hotelId){
		return new ResponseEntity<>(hotelService.getHotelById(hotelId),HttpStatus.FOUND);
	}
	
	@PutMapping("/{hotelId}")
	public ResponseEntity<HotelDTO> updateHotel(@PathVariable String hotelId, @RequestBody HotelDTO hotel){
		return new ResponseEntity<>(hotelService.updateHotel(hotelId, hotel),HttpStatus.OK);
	}
	
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<String> deleteHotel(@PathVariable String hotelId){
		hotelService.deleteHotel(hotelId);
		return new ResponseEntity<>("Hotel Deleted Succesfully",HttpStatus.OK);
	}
}
