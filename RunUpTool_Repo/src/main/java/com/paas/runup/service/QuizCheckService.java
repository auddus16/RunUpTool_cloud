package com.paas.runup.service;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paas.runup.dao.QuizCheckDAO;
import com.paas.runup.dto.QuizCheckDTO;

@Service
@MapperScan(basePackages="com.paas.runup.dao")
public class QuizCheckService implements QuizCheckDAO{

	@Autowired
	private QuizCheckDAO quizCheckDAO;
	
	@Override
	public List<QuizCheckDTO> selectQuizCheckListByQuiz(int q_no) throws Exception {
		// TODO Auto-generated method stub
		List<QuizCheckDTO> quizCheckList= quizCheckDAO.selectQuizCheckListByQuiz(q_no);
		return quizCheckList;
	}
	
	@Override
	public List<QuizCheckDTO> selectQuizCheckListByStudent(int s_no) throws Exception {
		// TODO Auto-generated method stub
		List<QuizCheckDTO> quizCheckList= quizCheckDAO.selectQuizCheckListByStudent(s_no);
		return quizCheckList;
	}

	@Override
	public void insertQuizCheck(QuizCheckDTO quizCheck) throws Exception {
		// TODO Auto-generated method stub
		quizCheckDAO.insertQuizCheck(quizCheck);
	}

	
}
