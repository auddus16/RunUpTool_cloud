package com.paas.runup.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.paas.runup.dao.UserDAO;
import com.paas.runup.dto.UserDTO;

public class UserService implements UserDAO{
	
	@Autowired
	UserDAO userDAO;
	
	@Override
	public Optional<UserDTO> findByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		
		return userDAO.findByEmail(email);
	}

}
