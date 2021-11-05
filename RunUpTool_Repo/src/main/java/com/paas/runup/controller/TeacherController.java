package com.paas.runup.controller;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.paas.runup.dto.TeacherDTO;
import com.paas.runup.email.EmailUtil;
import com.paas.runup.service.TeacherService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private EmailUtil emailUtil;

	@ResponseBody

	@ApiOperation("선생님 - 마이페이지 조회")
	@RequestMapping(value = "/getTeacher", method = RequestMethod.GET)
	public TeacherDTO getTeacher(HttpServletRequest request) throws Exception {
		log.info("선생님테이블 전체 검색 메소드-START");
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		int t_no = ((int) claims.get("user_no"));
		return teacherService.selectTeacher(t_no);
	}

	@ApiOperation(value = "선생님 - 이메일로 선생님번호 조회", notes = "[비밀번호 찾기]에서 입력받은 [이메일]로 [선생님번호]를 조회힌다.(-1은 가입된 학생 없음.)")
	@RequestMapping(value = "/getStudentByEmail/{email}", method = RequestMethod.GET)
	public int getStudentByEmail(HttpServletRequest request, @PathVariable String email) throws Exception {
		log.info("학생 정보 조회");
		TeacherDTO t = teacherService.selectTeacherByEmail(email);
		if (t == null) {
			log.info("선생님 정보 없음");
			return -1;
		}
		return t.getT_no();
	}

	@ApiOperation("선생님 - 회원가입")
	@RequestMapping(value = "/signupTeacher", method = RequestMethod.POST)
	public void signupTeacher(@RequestBody TeacherDTO t) throws Exception {
		log.info("선생님테이블 삽입 메소드-START");
		teacherService.insertTeacher(t);
	}

	@ApiOperation(value = "선생님 - 회원가입 이메일 인증", notes = "선생님 회원가입을 위해 이메일을 인증한다.")
	@RequestMapping(value = "/TeacherEmail/{email}", method = RequestMethod.POST)
	public boolean saveLocation(@PathVariable String email) {
		log.info("선생님 이메일 인증 시작");

		emailUtil.sendEmail(email, "[RunUpTool]회원가입 인증", "RunUpToolcloud22.paas-ta.org");
		return true;
	}

	@ApiOperation("선생님 - 회원정보 수정")
	@RequestMapping(value = "/updateTeacher", method = RequestMethod.PUT)
	public void updateTeacher(HttpServletRequest request, @RequestBody TeacherDTO t) throws Exception {
		log.info("선생님테이블 갱신 메소드-START");
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		t.setT_no((int) claims.get("user_no"));
		teacherService.updateTeacher(t);
	}

	@ApiOperation("선생님 - 회원정보 탈퇴")
	@RequestMapping(value = "/deleteTeacher", method = RequestMethod.DELETE)
	public void deleteTeacher(HttpServletRequest request) throws Exception {
		log.info("선생님테이블 삭제 메소드-START");
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		int t_no = (int) claims.get("user_no");
		teacherService.deleteTeacher(t_no);
	}

	@ApiOperation(value = "선생님 - 아이디찾기")
	@RequestMapping(value = "/searchTeacherID/{t_name}/{t_email}", method = RequestMethod.GET)
	public String searchTeacherID(@PathVariable String t_name, @PathVariable String t_email) throws Exception {
		log.info("선생님아이디찾기 메소드-START");
		String t_id = teacherService.searchTeacherID(t_name, t_email);

		if (t_id != null) {
			log.info("선생님 회원정보 있음");
			return t_id;
		} else {
			log.info("선생님 회원정보 없음");
			return null;
		}

	}

	@ApiOperation(value = "선생님 - 비밀번호찾기")
	@RequestMapping(value = "/searchTeacherPW/{t_name}/{t_id}/{t_email}", method = RequestMethod.GET)
	public void searchTeacherPW(@PathVariable String t_name, @PathVariable String t_id, @PathVariable String t_email)
			throws Exception {
		log.info("선생님비밀번호찾기 메소드-START");
		TeacherDTO teacherDTO = teacherService.searchTeacherPW(t_name, t_id, t_email);

		if (teacherDTO != null) {
			log.info("선생님 회원정보 있음");
			log.info("이메일 전송!");
			emailUtil.sendEmail(t_email, "[RunUpTool]회원 인증", "RunUpToolcloud22.paas-ta.org");
		} else {
			log.info("선생님 회원정보 없음");
		}

	}

	@ApiOperation(value = "선생님 - 비밀번호 재설정", notes = "선생님 비밀번호를 재설정한다.") // 비밀번호 찾기에서 비밀번호 재설정
	@RequestMapping(value = "/updateTeacherPW/{t_no}/{t_password}", method = RequestMethod.PUT)
	public void updateTeacherPW(@PathVariable int t_no, @PathVariable String t_password) throws Exception {
		log.info("선생님 비밀번호 재설정");

		teacherService.updateTeacherPW(t_no, t_password);
	}

	@ApiOperation(value = "선생님 - 비밀번호 재설정", notes = "선생님 비밀번호를 재설정한다.") // 마이페이지에서 선생님 비밀번호 재설정
	@RequestMapping(value = "/updateTeacherPW/{t_password}", method = RequestMethod.PUT)
	public void uupdateSTeacherPW(HttpServletRequest request, @PathVariable String t_password) throws Exception {
		log.info("선생님비밀번호재설정 메소드-START");
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		int t_no = (int) claims.get("user_no");
		teacherService.updateTeacherPW(t_no, t_password);
	}

}