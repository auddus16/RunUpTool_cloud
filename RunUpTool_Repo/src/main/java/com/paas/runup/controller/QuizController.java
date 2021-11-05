package com.paas.runup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.paas.runup.dto.QuizDTO;
import com.paas.runup.service.QuizService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@ResponseBody
	
	@ApiOperation(value = "퀴즈 리스트 조회", notes = "선생님이 수업에 추가한 퀴즈 리스트를 조회한다.")
	@ApiImplicitParam(name="c_no", value="수업 번호", example = "1")
	@RequestMapping(value="/quiz/getQuizAll/{c_no}", method=RequestMethod.GET)
	public List<QuizDTO> getQuizAll(@PathVariable int c_no) throws Exception{
		
		log.info("퀴즈 테이블 전체 검색");
		
		return quizService.selectQuizList(c_no);
	}
	
	@ApiOperation(value="퀴즈 상세 조회", notes="q_no를 통해 퀴즈를 상세 조회한다.")
	@ApiImplicitParam(name="q_no", value="퀴즈 번호", example = "1")
	@RequestMapping(value="/quiz/getQuizDetail/{q_no}", method=RequestMethod.GET)
	public QuizDTO getQuizDetail(@PathVariable int q_no) throws Exception{
		
		log.info("퀴즈 상세 검색");
		
		return quizService.selectQuiz(q_no);
	}
	
	@ApiOperation(value="새로운 퀴즈 추가", notes="새로운 퀴즈를 추가한다.")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name="q_no", value="퀴즈 번호", dataType="int"),
//		@ApiImplicitParam(name="c_no", value="수업 번호", dataType="int"),
//		@ApiImplicitParam(name="q_ques", value="퀴즈 문제", dataType="string"),
//		@ApiImplicitParam(name="q_ans", value="퀴즈 답", dataType="string"),
//		@ApiImplicitParam(name="q_type", value="퀴즈 유형(True:서술형 False:단답형 )", dataType="boolean"),
//		@ApiImplicitParam(name="q_timelimit", value="퀴즈 제한 시간", dataType="string")
//		})
	@RequestMapping(value="/quiz/addQuiz", method=RequestMethod.POST)
	public void addQuiz(@RequestBody QuizDTO quiz) throws Exception{
		log.info("퀴즈 추가");
//		QuizDTO quiz = new QuizDTO();
//		
//		//test
//		Time time = new Time(0, 3, 0);
//		quiz.setC_no(1);
//		quiz.setQ_ques("문제");
//		quiz.setQ_ans("답");
//		quiz.setQ_type(false);
//		quiz.setQ_timelimit(time);
//		quizService.insertQuiz(quiz);
		
		quizService.insertQuiz(quiz);
	}
	
	
	@ApiOperation(value="퀴즈 수정", notes="q_no를 통해 퀴즈를 수정한다.")
	@RequestMapping(value="/quiz/modifyQuiz/{q_no}", method=RequestMethod.PUT)
	public void modifyQuiz(@PathVariable int q_no, @RequestBody QuizDTO quiz) throws Exception{
		log.info("퀴즈 수정");
		
//		//test
//		QuizDTO quiz = quizService.selectQuiz(3);
//		quiz.setQ_ans("답 수정");
//		quizService.updateQuiz(quiz);
		
		quizService.updateQuiz(quiz);
		
	}
	
	@ApiOperation(value="퀴즈 삭제", notes="q_no를 통해 퀴즈를 삭제한다.")
	@ApiImplicitParam(name="q_no", value="퀴즈 번호", example = "1")
	@RequestMapping(value="/quiz/deleteQuiz/{q_no}", method= {RequestMethod.DELETE})
	public void deleteQuiz(@PathVariable int q_no) throws Exception{
		log.info("퀴즈 삭제");
		
//		//test
//		quizService.deleteQuiz(3);
//		String redirect_uri = "/quiz/getQuizAll";
//		response.sendRedirect(redirect_uri);
		
		quizService.deleteQuiz(q_no);
	}
	
	
}
