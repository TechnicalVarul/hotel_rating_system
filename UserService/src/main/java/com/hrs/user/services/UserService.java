package com.hrs.user.services;

import java.util.List;

import com.hrs.user.dtos.UserDTO;

public interface UserService {
	
	UserDTO saveUser(UserDTO user);
	List<UserDTO> getAllUsers();
	UserDTO getUserById(String userId);
	void deleteUser(String userId);
	UserDTO updateUser(UserDTO user,String userId);
	

}
