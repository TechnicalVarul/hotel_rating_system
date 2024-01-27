package com.hrs.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "micro_hotel")
@Data
public class Hotel {
	@Id
	@Column(name = "HOTELID")
	private String hotelId;
	
	@Column(name = "HOTELNAME")
	private String hotelName;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "ABOUT")
	private String about;

}
