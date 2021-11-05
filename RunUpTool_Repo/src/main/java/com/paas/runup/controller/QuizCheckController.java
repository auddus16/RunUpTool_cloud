package com.paas.runup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.paas.runup.dto.QuizCheckDTO;
import com.paas.runup.service.QuizCheckService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class QuizCheckController {
	
	@Autowired
	private QuizCheckService quizCheckService;
	
	@ResponseBody
	
	@ApiOperation(value = "선생님 - 퀴즈 제출 리스트 조회", notes = "퀴즈 제출 리스트를 조회한다.")
	@ApiImplicitParam(name="q_no", value="퀴즈 번호", example = "1")
	@RequestMapping(value="/quiz/getQuizCheckByQuiz/{q_no}", method=RequestMethod.GET)
	public List<QuizCheckDTO> getQuizCheckByQuiz(@PathVariable int q_no) throws Exception{
		
		log.info("퀴즈 제출 리스트 검색");
		
		return quizCheckService.selectQuizCheckListByQuiz(q_no);
	}
	
	@ApiOperation(value = "학생 - 제출한 퀴즈 리스트 조회", notes = "자신이 제출한 퀴즈 리스트를 조회한다.")
	@ApiImplicitParam(name="s_no", value="학생 번호", example = "1")
	@RequestMapping(value="/quiz/getQuizCheckByStudent/{s_no}", method=RequestMethod.GET)
	public List<QuizCheckDTO> getQuizCheckByStudent(@PathVariable int s_no) throws Exception{
		
		log.info("제출한 퀴즈 리스트 검색");
		
		return quizCheckService.selectQuizCheckListByStudent(s_no);
	}
	
	@ApiOperation(value="제출한 퀴즈 추가", notes="학생이 제출한 퀴즈를 추가한다.")
	@RequestMapping(value="/quiz/addQuizCheck", method=RequestMethod.POST)
	public void addQuizCheck(@RequestBody QuizCheckDTO quizcheck) throws Exception{
		log.info("제출한 퀴즈 추가");
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
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		quizCheckService.insertQuizCheck(quizcheck);
	}
	
	
	
}
