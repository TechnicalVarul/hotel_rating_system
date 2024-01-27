package com.hrs.rating.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "micro_rating")
@Data
public class Rating {
	@Id
	@Column(name = "RATINGID")
	private String ratingId;
	
	@Column(name = "HOTELID")
	private String hotelId;
	
	@Column(name = "USERID")
	private String userId;
	
	@Column(name = "RATE")
	private byte rate; // 1 to 5
	
	@Column(name = "FEEDBACK")
	private String feedback;
}
