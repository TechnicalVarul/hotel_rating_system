package com.hrs.user.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {

	private String userId;
	private String name;
	private String email;
	private String about;
	private List<Ratings> ratings = new ArrayList<>();
}
