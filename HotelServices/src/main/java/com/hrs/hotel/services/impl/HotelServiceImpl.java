package com.hrs.hotel.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.hotel.dtos.HotelDTO;
import com.hrs.hotel.dtos.Ratings;
import com.hrs.hotel.entities.Hotel;
import com.hrs.hotel.exceptions.ResourceNotFoundException;
import com.hrs.hotel.repos.HotelRepo;
import com.hrs.hotel.services.HotelService;
import com.hrs.hotel.services.RatingService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HotelRepo hotelRepo;
	
	//@Autowired
	//private RestTemplate restTemplate;
	
	@Autowired
	private RatingService ratingService;
	
	@Override
	public HotelDTO createHotel(HotelDTO hotel) {
		Hotel savedHotel = hotelRepo.save(modelMapper.map(hotel, Hotel.class));
		return modelMapper.map(savedHotel, HotelDTO.class);
	}

	@Override
	public HotelDTO getHotelById(String hotelId) {
		HotelDTO fetchedHotel = modelMapper.map(hotelRepo.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("HOTEL", "hotelId", hotelId)), HotelDTO.class);
		
		//CALLING RATING_SERVICE
		
		//USING REST_TEMPLET
		//ArrayList<Ratings> ratings= restTemplate.getForObject("http://RATING-SERVICE/api/rating/hotel/"+hotelId, ArrayList.class);
		List<Ratings> ratings= ratingService.getRatingsByHoteID(hotelId);
		fetchedHotel.setRatings(ratings);
		
		return fetchedHotel;
	}

	@Override
	public List<HotelDTO> getAllHotels() {
		List<HotelDTO> hotels = hotelRepo.findAll().stream().map(e->modelMapper.map(e, HotelDTO.class)).toList();
		for(HotelDTO hotel : hotels) {
			//ArrayList<Ratings> ratings= restTemplate.getForObject("http://RATING-SERVICE/api/rating/hotel/"+hotel.getHotelId(), ArrayList.class);
			List<Ratings> ratings= ratingService.getRatingsByHoteID(hotel.getHotelId());
			hotel.setRatings(ratings);
		}
		return hotels;
	}

	@Override
	public void deleteHotel(String hotelId) {
		Hotel fetchedHotel = hotelRepo.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("HOTEL", "hotelId", hotelId));
		
		hotelRepo.delete(fetchedHotel);
		
	}

	@Override
	public HotelDTO updateHotel(String hotelId, HotelDTO hotel) {
		Hotel fetchedHotel = hotelRepo.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("HOTEL", "hotelId", hotelId));
		fetchedHotel.setHotelName(hotel.getHotelName());
		fetchedHotel.setLocation(hotel.getLocation());
		fetchedHotel.setAbout(hotel.getAbout());
		
		Hotel savedHotel =  hotelRepo.save(fetchedHotel);
		return modelMapper.map(savedHotel, HotelDTO.class);
	}

}
