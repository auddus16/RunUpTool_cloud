package com.paas.runup.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.paas.runup.dto.ClassDTO;

@Mapper
@Repository
public interface ClassDAO {
	List<ClassDTO> selectClassByTeacher(int t_no) throws Exception;
	
	ClassDTO selectClassByClass(int c_no) throws Exception;
	
	int updateClass(ClassDTO c) throws Exception;
	
	int deleteClass(int c_no) throws Exception;
	
	int insertClass(ClassDTO c) throws Exception;	
	
	int addStudent(int c_no) throws Exception; //학생수 1 증가
	
	int subStudent(int c_no) throws Exception; //학생수 1 감소
}
