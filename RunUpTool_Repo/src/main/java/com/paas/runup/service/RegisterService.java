package com.paas.runup.service;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paas.runup.dao.RegisterDAO;
import com.paas.runup.dto.RegisterDTO;

@Service
@MapperScan(basePackages="com.paas.runup.dao")
public class RegisterService implements RegisterDAO{
	
	@Autowired
	RegisterDAO registerDAO;
	
	@Override
	public List<RegisterDTO> selRegisterAllByClass(int c_no) throws Exception {
		// TODO Auto-generated method stub
		return registerDAO.selRegisterAllByClass(c_no);
	}

	@Override
	public List<RegisterDTO> selRegisterAllByStudent(int s_no) throws Exception {
		// TODO Auto-generated method stub
		return registerDAO.selRegisterAllByStudent(s_no);
	}

	@Override
	public int delRegisterByClass(int c_no) throws Exception {
		// TODO Auto-generated method stub
		return registerDAO.delRegisterByClass(c_no);
	}

	@Override
	public int delRegisterByStudent(int s_no) throws Exception {
		// TODO Auto-generated method stub
		return registerDAO.delRegisterByStudent(s_no);
	}

	@Override
	public int insertRegister(RegisterDTO r) throws Exception {
		// TODO Auto-generated method stub
		return registerDAO.insertRegister(r);
	}

	@Override
	public int deleteRegister(RegisterDTO r) throws Exception {
		// TODO Auto-generated method stub
		return registerDAO.deleteRegister(r);
	}

}