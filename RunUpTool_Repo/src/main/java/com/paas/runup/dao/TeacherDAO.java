package com.paas.runup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.paas.runup.dto.TeacherDTO;

@Mapper
@Repository
public interface TeacherDAO extends UserDAO{
   TeacherDTO selectTeacher(int t_no) throws Exception;
   TeacherDTO selectTeacherByEmail(String email) throws Exception;
   void insertTeacher (TeacherDTO t) throws Exception;
   void updateTeacher (TeacherDTO t) throws Exception;
   void deleteTeacher (int t_no) throws Exception;
   
   TeacherDTO getTeacherByIDPW(String t_id,String t_password) throws Exception;
   String searchTeacherID(String t_name,String t_email) throws Exception;
   TeacherDTO searchTeacherPW(String t_name, String t_id, String t_email) throws Exception;
   TeacherDTO updateTeacherPW(int t_no, String t_password) throws Exception;
}