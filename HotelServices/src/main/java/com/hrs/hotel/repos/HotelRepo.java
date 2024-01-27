package com.hrs.hotel.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.hotel.entities.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String>{

}
