package com.paas.runup.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.paas.runup.dto.AttendDTO;
import com.paas.runup.dto.ClassDTO;
import com.paas.runup.dto.MsgRoom;
import com.paas.runup.dto.RegisterDTO;
import com.paas.runup.email.EmailUtil;
import com.paas.runup.service.AttendService;
import com.paas.runup.service.ClassService;
import com.paas.runup.service.MsgService;
import com.paas.runup.service.RegisterService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/class")
public class ClassController { // 선생님, 학생 - 수업 관리 (수업, 출석, 등록)
	
	@Autowired
	private ClassService classService;

	@Autowired
	private RegisterService registerService;

	@Autowired
	private AttendService attendService;

	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private MsgService msgService;

	@ResponseBody

	@ApiOperation(value = "선생님 - 수업 리스트 조회", notes = "선생님의 수업 리스트를 조회한다.")
	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public List<ClassDTO> getClassList(HttpServletRequest request) throws Exception {
		
		log.info("수업 리스트 조회 메소드 시작");
		// 선생님 번호로 수업리스트 검색

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		int t_no = (int) claims.get("user_no");// ++t_no는 세션값 저장

		return classService.selectClassByTeacher(t_no);
	}

	@ApiOperation(value = "수업 상세정보 조회", notes = "하나의 수업 정보를 조회한다.")
	@ApiImplicitParam(name = "c_no", value = "수업 번호", dataType = "int", example = "1")
	@RequestMapping(value = "/detail/{c_no}", method = RequestMethod.GET)
	public ClassDTO getClass(@PathVariable int c_no) throws Exception {
		
		log.info("수업 상세정보 조회 메소드 시작");

		return classService.selectClassByClass(c_no);
	}

	@ApiOperation(value = "수업 출결정보 조회", notes = "해당 수업의 출결 정보를 조회한다.")
	@ApiImplicitParams({ @ApiImplicitParam(name = "c_no", value = "수업 번호", dataType = "int", example = "1"),
			@ApiImplicitParam(name = "day", value = "조회 날짜", dataType = "String", example = "YYYYMMDD") })
	@RequestMapping(value = "/teacher/attend/{c_no}/{day}", method = RequestMethod.GET)
	public List<AttendDTO> getAttendList(@PathVariable int c_no, @PathVariable String day) throws Exception {
		
		log.info("수업 출결정보 조회 메소드 시작");
		
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("c_no", c_no);
		hm.put("day", day);

		return attendService.selectAttendByDate(hm);
	}

	@ApiOperation(value = "선생님 - 학생 출결 정보 수정", notes = "선생님이 학생 출결 정보를 수정한다.")
	@ApiImplicitParams({ @ApiImplicitParam(name = "a_no", value = "출결 번호", dataType = "int", example = "1"),
			@ApiImplicitParam(name = "a_state", value = "출결 상태(0:출석 1:지각 2:결석)", dataType = "int", example = "1") })
	@RequestMapping(value = "/teacher/attend/{a_no}/{a_state}", method = RequestMethod.PUT)
	public boolean modifyAttend(@PathVariable int a_no, @PathVariable int a_state) throws Exception {
		
		log.info("학생 출결 정보 수정 메소드 시작");

		if (attendService.updateAttendState(a_no, a_state) == 0) {
			System.out.println("학생 출결 정보 수정 실패!!");
			return false;
		}

		System.out.println("학생 출결 정보 수정 완료");

		return true;

	}

	@ApiOperation(value = "선생님 - 수업에서 해당 학생 삭제", notes = "선생님이 수업에서 학생을 삭제한다.")
	@RequestMapping(value = "/teacher/attend/del", method = RequestMethod.DELETE)
	public boolean deleteRegister(@RequestBody RegisterDTO r) throws Exception {
		
		log.info("학생 등록 삭제 메소드 시작");

		// 등록 테이블 삭제
		if (registerService.deleteRegister(r) != 0) {
			// 수업 테이블 학생 수 감소
			if (classService.subStudent(r.getC_no()) == 0) {
				log.info("수업 학생 수 감소 실패!!");
			}
			log.info("수업 삭제 완료");

			return true;

		}

		log.info("수업 삭제 실패!!");

		return false;
	}

	@ApiOperation(value = "선생님 - 새로운 수업 추가", notes = "선생님이 새로운 수업을 생성한다.")
	@RequestMapping(value = "/teacher/new", method = RequestMethod.POST)
	public boolean addNewClass(HttpServletRequest request, @RequestBody ClassDTO c) throws Exception {

		log.info("새로운 수업 추가 메소드 시작");

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		c.setT_no((int) claims.get("user_no"));// ++세션에 저장된 선생님 번호로 c.setT_no(---)

		if (classService.insertClass(c) == 0) {
			log.info("새로운 수업 추가 실패!!");

			return false;
		}

		log.info("새로운 수업 추가 완료");

		return true;
	}

	@ApiOperation(value = "선생님 - 학생 초대", notes = "선생님이 수업에 학생을 초대한다.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "emailMap", value = "이메일 리스트", dataType = "Object", example = "{\r\n"
					+ "  \"email\" : [\"email1@gmail.com\", \"email2@gmail.com\"]\r\n" + "}"),
			@ApiImplicitParam(name = "c_name", value = "수업 이름", dataType = "String", example = "수학1반") })
	@RequestMapping(value = "/teacher/email/{c_name}", method = RequestMethod.POST)
	public boolean saveLocation(HttpServletRequest request, @RequestBody HashMap<String, List<Object>> emailMap,
			@PathVariable String c_name) {
		log.info("학생 초대 메소드 시작");

		List<Object> emailList = new ArrayList<Object>();
		emailList = emailMap.get("email");

//		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
//				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		// ++선생님 이름(로그인 정보), 수업 정보를 이메일에 함께 넣어보자.
		// 학생이 존재하나요??-굳이 해야하나?
		for (Object s : emailList) {
			emailUtil.sendEmail((String) s, "[RunUpTool 수업 초대]", "초대 테스트 메일 입니다.");
			log.info((String) s + "초대합니다.");
		}

		return true;
	}

	@ApiOperation(value = "학생 수업 등록", notes = "수업에 학생을 등록시킨다.")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public boolean registerStudent(@RequestBody RegisterDTO r) throws Exception {

		log.info("학생 수업 등록 조회 메소드 시작");
		// 등록 테이블 삽입
		if (registerService.insertRegister(r) != 0) {

			log.info("수업 학생 수 증가 메소드 시작");
			// 수업 테이블 학생 수 증가
			if (classService.addStudent(r.getC_no()) == 0) {
				log.info("수업 학생 수 증가 실패!!");
			}

			log.info("학생 수업 등록 성공");

			return true;
		}

		log.info("학생 수업 등록 실패!!");

		return false;

	}

	@ApiOperation(value = "선생님 - 수업 정보 수정", notes = "선생님이 수업 정보를 수정한다.")
	@RequestMapping(value = "/teacher/{c_no}", method = RequestMethod.PUT)
	public boolean modifyClass(@RequestBody ClassDTO c, @PathVariable int c_no) throws Exception {

		log.info("수업 정보 수정 메소드 시작");

		c.setC_no(c_no); // 수업번호 set해주기

		if (classService.updateClass(c) == 0) {
			log.info("수업 정보 수정 실패!!");
			return false;
		}

		log.info("수업 정보 수정 완료");

		return true;

	}

	@ApiOperation(value = "선생님 - 수업 삭제", notes = "선생님이 수업을 삭제한다.")
	@ApiImplicitParam(name = "c_no", value = "수업 번호", dataType = "int", example = "1")
	@RequestMapping(value = "teacher/del/{c_no}", method = RequestMethod.DELETE)
	public boolean deleteClass(@PathVariable int c_no) throws Exception {

		log.info("수업 삭제 메소드 시작");

		if (classService.deleteClass(c_no) == 0) {
			log.info("수업 삭제 실패!!");

			return false;
		}

		log.info("수업 삭제 완료");

		return true;
	}

	/*
	 * claim 쓰는 법!!!!
	 */
	@ApiOperation(value = "학생 - 수업 리스트 조회", notes = "학생의 수업 리스트를 조회한다.")
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public List<ClassDTO> getClassList2(HttpServletRequest request) throws Exception {

		log.info("수업 리스트 조회 메소드 시작");
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		int s_no = (int) claims.get("user_no");// ++s_no는 세션값 저장

		List<RegisterDTO> registerList = registerService.selRegisterAllByStudent(s_no);// 등록 테이블 먼저 검색

		List<ClassDTO> classList = new ArrayList<ClassDTO>(); // 수업정보 저장
		for (RegisterDTO r : registerList) {
			log.info(""+r.getC_no());
			classList.add(classService.selectClassByClass(r.getC_no()));
		}

		return classList;
	}

	@ApiOperation(value = "학생 - 수업 출결 조회", notes = "학생의 수업 출결 리스트를 조회한다.")
	@RequestMapping(value = "/student/attend/{c_no}", method = RequestMethod.GET)
	public List<AttendDTO> getAttendList(HttpServletRequest request, @PathVariable int c_no) throws Exception {

		log.info("수업 출결 조회 메소드 시작");

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();

		int s_no = (int) claims.get("user_no");// ++s_no는 세션값 저장

		return attendService.selectAttendList(s_no, c_no);
	}

	@ApiOperation(value = "학생 - 입장할 수업 roomId 찾기", notes = "학생이 입장할 수업의 roomId를 찾는다. c_no를 통해 수업이 열려있는지 확인한다.")
	@RequestMapping(value = "/student/getroomid/{c_no}", method = RequestMethod.GET)
	public MsgRoom getRoomId(@PathVariable int c_no) throws Exception {

		log.info("수업 roomId 찾기 메소드 시작");

		return msgService.selectRoomId(c_no);
	}
}
