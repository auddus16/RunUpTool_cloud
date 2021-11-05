package com.paas.runup.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.paas.runup.dto.QuizDTO;
@Mapper
@Repository
public interface QuizDAO {
	List<QuizDTO> selectQuizList(int c_no) throws Exception;
	QuizDTO selectQuiz(int q_no) throws Exception;
	void insertQuiz(QuizDTO quiz) throws Exception;
	void updateQuiz (QuizDTO quiz) throws Exception;
	void deleteQuiz (int q_no) throws Exception;
}