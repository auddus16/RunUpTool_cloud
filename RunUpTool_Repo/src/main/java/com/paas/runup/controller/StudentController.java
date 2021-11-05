package com.paas.runup.controller;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.paas.runup.dto.StudentDTO;
import com.paas.runup.email.EmailUtil;
import com.paas.runup.service.StudentService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private EmailUtil emailUtil;

	@ResponseBody

	@ApiOperation(value = "학생 - 마이페이지 조회", notes = "로그인 한 학생 정보를 조회한다.")
	@RequestMapping(value = "/getStudent", method = RequestMethod.GET)
	public StudentDTO getStudent(HttpServletRequest request) throws Exception {

		log.info("학생 정보 조회");

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		int s_no = (int) claims.get("user_no");

		return studentService.selectStudent(s_no);
	}

	@ApiOperation(value = "학생 - 이메일로 학생번호 조회", notes = "[비밀번호 찾기]에서 입력받은 [이메일]로 [학생번호]를 조회힌다.(-1은 가입된 학생 없음.)")
	@RequestMapping(value = "/getStudentByEmail/{email}", method = RequestMethod.GET)
	public int getStudentByEmail(HttpServletRequest request, @PathVariable String email) throws Exception {

		log.info("학생 정보 조회");

		StudentDTO s = studentService.selectStudentByEmail(email);
		if (s == null) {
			log.info("학생 정보 없음");
			return -1;
		}
		return s.getS_no();
	}

	@ApiOperation(value = "학생 - 회원가입", notes = "학생 회원가입을 한다.")
	@RequestMapping(value = "/signupStudent", method = RequestMethod.POST)
	public void signupStudent(@RequestBody StudentDTO student) throws Exception {
		log.info("학생 회원가입");

		studentService.insertStudent(student);
	}

	@ApiOperation(value = "학생 - 회원가입 이메일 인증", notes = "학생 회원가입을 위해 이메일을 인증한다.")
	@RequestMapping(value = "/StudentEmail/{email}", method = RequestMethod.POST)
	public boolean saveLocation(@PathVariable String email) {
		log.info("학생 이메일 인증 시작");

		emailUtil.sendEmail(email, "[RunUpTool]회원가입 인증", "RunUpToolcloud22.paas-ta.org");
		return true;
	}

	@ApiOperation(value = "학생 - 회원 정보 수정", notes = "회원 정보를 수정한다.")
	@RequestMapping(value = "/updateStudentInfo", method = RequestMethod.PUT)
	public void updateStudentInfo(HttpServletRequest request, @RequestBody StudentDTO student) throws Exception {
		log.info("학생 회원정보 수정");

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		student.setS_no((int) claims.get("user_no"));

		studentService.updateStudent(student);
	}

	@ApiOperation(value = "학생 - 회원 탈퇴", notes = "학생 회원 탈퇴를 한다.")
	@RequestMapping(value = "/deleteStudent", method = RequestMethod.DELETE)
	public void deleteStudent(HttpServletRequest request) throws Exception {
		log.info("학생 회원 탈퇴");

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		int s_no = (int) claims.get("user_no");

		studentService.deleteStudent(s_no);
	}

	@ApiOperation(value = "학생 - 아이디 찾기", notes = "학생 아이디를 찾는다.")
	@RequestMapping(value = "/searchStudentID/{s_name}/{s_email}", method = RequestMethod.GET)
	public String searchStudentID(@RequestParam String s_name, @RequestParam String s_email) throws Exception {
		log.info("학생 아이디 찾기");

		String s_id = studentService.searchStudentID(s_name, s_email);

		if (s_id != null) {
			log.info("학생 회원정보 있음 :" + s_id);
			return s_id;

		} else {
			log.info("학생 회원정보 없음");
			return null;
		}

	}

	@ApiOperation(value = "학생 - 비밀번호찾기")
	@RequestMapping(value = "/searchStudentPW/{s_name}/{s_id}/{s_email}", method = RequestMethod.GET)
	public void searchStudentPW(@PathVariable String s_name, @PathVariable String s_id, @PathVariable String s_email)
			throws Exception {

		log.info("학생비밀번호찾기");
		StudentDTO studentDTO = studentService.searchStudentPW(s_name, s_id, s_email);

		if (studentDTO != null) {
			log.info("학생 회원정보 있음");
			log.info("이메일 전송!");
			emailUtil.sendEmail(s_email, "[RunUpTool]회원 인증", "RunUpToolcloud22.paas-ta.org");
		} else {
			log.info("학생 회원정보 없음");
		}

	}

	@ApiOperation(value = "학생 - 비밀번호 재설정", notes = "학생 비밀번호를 재설정한다.") // 비밀번호 찾기에서 비밀번호 재설정
	@RequestMapping(value = "/updateStudentPW/{s_no}/{s_password}", method = RequestMethod.PUT)
	public void updateStudentPW(@PathVariable int s_no, @PathVariable String s_password) throws Exception {
		log.info("학생 비밀번호 재설정");

		studentService.updateStudentPW(s_no, s_password);
	}

	@ApiOperation(value = "학생 - 비밀번호 재설정", notes = "학생 비밀번호를 재설정한다.") // 마이페이지에서 비밀번호 재설정
	@RequestMapping(value = "/updateStudentPW2/{s_password}", method = RequestMethod.PUT)
	public void updateStudentPW(HttpServletRequest request, @PathVariable String s_password) throws Exception {
		log.info("학생 비밀번호 재설정");
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		int s_no = (int) claims.get("user_no");
		studentService.updateStudentPW(s_no, s_password);
	}
}