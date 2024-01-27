package com.hrs.user.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.user.entities.User;

public interface UserRepo extends JpaRepository<User, String> {

}
