package com.hrs.user.dtos;

import lombok.Data;

@Data
public class Ratings {

	private String ratingId;
	private String userId;
	private String hotelId;
	private byte rate; // 1 to 5
	private String feedback;
}
