package com.paas.runup.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.paas.runup.dto.UserDTO;

@Mapper
@Repository
public interface UserDAO {
	Optional<UserDTO> findByEmail(String email) throws Exception;
	
}
