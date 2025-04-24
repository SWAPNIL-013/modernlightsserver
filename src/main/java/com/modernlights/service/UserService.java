package com.modernlights.service;

import com.modernlights.exception.UserException;
import com.modernlights.model.User;

public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;


}
