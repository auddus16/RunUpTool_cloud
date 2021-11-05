package com.paas.runup.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.paas.runup.dto.QuizCheckDTO;
@Mapper
@Repository
public interface QuizCheckDAO {
	List<QuizCheckDTO> selectQuizCheckListByQuiz(int q_no) throws Exception;
	List<QuizCheckDTO> selectQuizCheckListByStudent(int q_no) throws Exception;
	void insertQuizCheck(QuizCheckDTO quizCheck) throws Exception;
//	void updateQuizCheck (QuizCheckDTO quizCheck) throws Exception;
//	void deleteQuizCheck (int qc_no) throws Exception;
}
