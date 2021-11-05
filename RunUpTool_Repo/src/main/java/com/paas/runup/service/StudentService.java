package com.paas.runup.service;

import java.util.Optional;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paas.runup.dao.StudentDAO;
import com.paas.runup.dao.UserDAO;
import com.paas.runup.dto.StudentDTO;
import com.paas.runup.dto.UserDTO;

@Service
@MapperScan(basePackages = "com.paas.runup.dao")
public class StudentService implements StudentDAO, UserDAO {

	@Autowired
	StudentDAO studentDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public StudentDTO selectStudent(int s_no) throws Exception {
		// TODO Auto-generated method stub
		StudentDTO student = studentDAO.selectStudent(s_no);
		return student;
	}

	@Override
	public int insertStudent(StudentDTO student) throws Exception {
		// TODO Auto-generated method stub
		student.setS_password(passwordEncoder.encode(student.getS_password()));
		return studentDAO.insertStudent(student);
	}

	@Override
	public void updateStudent(StudentDTO student) throws Exception {
		// TODO Auto-generated method stub
		studentDAO.updateStudent(student);
	}

	@Override
	public void deleteStudent(int s_no) throws Exception {
		// TODO Auto-generated method stub
		studentDAO.deleteStudent(s_no);
	}

	@Override
	public StudentDTO getStudentByIDPW(String s_id, String s_password) throws Exception {
		// TODO Auto-generated method stub
		return studentDAO.getStudentByIDPW(s_id, s_password); // 아이디 비번 select
	}

	@Override
	public String searchStudentID(String s_name, String s_email) throws Exception {
		// TODO Auto-generated method stub
		return studentDAO.searchStudentID(s_name, s_email);
	}

	@Override
	public StudentDTO searchStudentPW(String s_name, String s_id, String s_email) throws Exception {
		// TODO Auto-generated method stub
		return studentDAO.searchStudentPW(s_name, s_id, s_email);
	}

	@Override
	public StudentDTO updateStudentPW(int s_no, String s_password) throws Exception {
		// TODO Auto-generated method stub
		// 비밀번호 인코딩
		return studentDAO.updateStudentPW(s_no, passwordEncoder.encode(s_password));

	}

	@Override
	public Optional<UserDTO> findByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.findByEmail(email);
	}

	@Override
	public StudentDTO selectStudentByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return studentDAO.selectStudentByEmail(email);
	}

}