package com.hrs.hotel.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class HotelDTO {

	private String hotelId;	
	private String hotelName;
	private String location;
	private String about;
	private List<Ratings> ratings = new ArrayList<>();

}
