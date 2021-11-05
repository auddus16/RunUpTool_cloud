package com.paas.runup.service;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paas.runup.dao.QuizDAO;
import com.paas.runup.dto.QuizDTO;

@Service
@MapperScan(basePackages="com.paas.runup.dao")
public class QuizService implements QuizDAO{

	@Autowired
	private QuizDAO quizDAO;
	
	@Override
	public List<QuizDTO> selectQuizList(int c_no) throws Exception {
		// TODO Auto-generated method stub
		List<QuizDTO> quizList= quizDAO.selectQuizList(c_no);
		return quizList;
	}

	@Override
	public QuizDTO selectQuiz(int q_no) throws Exception {
		// TODO Auto-generated method stub
		QuizDTO quiz = quizDAO.selectQuiz(q_no);
		return quiz;
	}

	@Override
	public void insertQuiz(QuizDTO quiz) throws Exception {
		// TODO Auto-generated method stub
		quizDAO.insertQuiz(quiz);
	}

	@Override
	public void updateQuiz(QuizDTO quiz) throws Exception {
		// TODO Auto-generated method stub
		quizDAO.updateQuiz(quiz);
		
	}

	@Override
	public void deleteQuiz(int q_no) throws Exception {
		// TODO Auto-generated method stub
		quizDAO.deleteQuiz(q_no);
	}

	
	

}
