package com.paas.runup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.paas.runup.dto.StudentDTO;

@Mapper
@Repository
public interface StudentDAO extends UserDAO{
   StudentDTO selectStudent(int s_no) throws Exception;
   StudentDTO selectStudentByEmail(String email) throws Exception;
   int insertStudent(StudentDTO student) throws Exception;
   void updateStudent(StudentDTO student) throws Exception;
   void deleteStudent(int s_no) throws Exception; 
   
   StudentDTO getStudentByIDPW(String s_id,String s_password) throws Exception;
   String searchStudentID(String s_name,String s_email) throws Exception;
   StudentDTO searchStudentPW(String s_name, String s_id, String s_email) throws Exception;
   StudentDTO updateStudentPW(int s_no, String s_password) throws Exception;
   
}