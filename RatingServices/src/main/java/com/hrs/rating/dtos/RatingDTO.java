package com.hrs.rating.dtos;

import lombok.Data;

@Data
public class RatingDTO {
	
	private String ratingId;
	private String hotelId;
	private String userId;
	private byte rate; // 1 to 5
	private String feedback;

}
