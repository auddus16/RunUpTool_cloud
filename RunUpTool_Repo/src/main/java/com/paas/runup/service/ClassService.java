package com.paas.runup.service;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paas.runup.dao.ClassDAO;
import com.paas.runup.dto.ClassDTO;

@Service
@MapperScan(basePackages="com.paas.runup.dao")
public class ClassService implements ClassDAO{
	
	@Autowired
	ClassDAO classDAO;
	
	@Override
	public List<ClassDTO> selectClassByTeacher(int t_no) throws Exception {
		
		return classDAO.selectClassByTeacher(t_no);
	}

	@Override
	public ClassDTO selectClassByClass(int c_no) throws Exception {
		
		return classDAO.selectClassByClass(c_no);
	}

	@Override
	public int updateClass(ClassDTO c) throws Exception {
		
		return classDAO.updateClass(c);
	}

	@Override
	public int deleteClass(int c_no) throws Exception {

		return classDAO.deleteClass(c_no);
	}

	@Override
	public int insertClass(ClassDTO c) throws Exception {
		
		return classDAO.insertClass(c);
	}

	@Override
	public int addStudent(int c_no) throws Exception {
		
		return classDAO.addStudent(c_no);
	}

	@Override
	public int subStudent(int c_no) throws Exception {
		
		return classDAO.subStudent(c_no);
	}

}
