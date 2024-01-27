package com.hrs.hotel.services;

import java.util.List;

import com.hrs.hotel.dtos.HotelDTO;

public interface HotelService {

	HotelDTO createHotel(HotelDTO hotel);
	HotelDTO getHotelById(String hotelId);
	List<HotelDTO> getAllHotels();
	void deleteHotel(String hotelId);
	HotelDTO updateHotel(String hotelId,HotelDTO hotel);
}
