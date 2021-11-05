package com.paas.runup.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paas.runup.dto.AttendDTO;
import com.paas.runup.dto.RegisterDTO;
import com.paas.runup.service.AttendService;
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
@RequestMapping(value="/attend")
public class AttendController {
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private AttendService attendService;
	
	@ApiOperation(value= "선생님 - 출결 시작하기", notes="선생님이 출결을 시작한다.")
	@ApiImplicitParam(name="c_no", value="수업 번호", dataType = "int", example= "1")
	@RequestMapping(value = "/start/{c_no}", method = RequestMethod.POST)
	public boolean startAttend(@PathVariable int c_no) throws Exception {
		
		//수업별로 학생리스트 검색한 후, 그 학생들 출석테이블에 insert
		List<RegisterDTO> registerList= registerService.selRegisterAllByClass(c_no);
		
		for(RegisterDTO r : registerList) {
			if(attendService.insertAttend(r.getS_no(), r.getC_no())==0){
				log.info("출결시작 실패!!");
				
				return false;
			}
			log.info(r.getC_no()+"에 "+r.getS_no()+"번 학생 추가");
			//System.out.println(r.getR_time());
		}
		
		log.info("출결 시작 완료");
			
		return true;
	}
	
	@ApiOperation(value= "학생 - 출석 체크", notes= "학생이 출석체크를 한다.")
	@ApiImplicitParams({
      @ApiImplicitParam(name="c_no", value="수업 번호", dataType = "int", example= "1"),
	})
	@RequestMapping(value= "/check/{c_no}", method= RequestMethod.PUT)
	public boolean modifyClass(HttpServletRequest request, @PathVariable int c_no) throws Exception {
		//먼저 오늘 출석시작한 수업에서 자신의 레코드를 검색한다.
		//그 레코드의 출석시작검색해준다.
		log.info("학생 출석 메소드 시작");
		
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("jwtpassword"))
				.parseClaimsJws(request.getHeader("jwt").substring(7)).getBody();
		int s_no= (int) claims.get("user_no");
		
		AttendDTO attendDTO= attendService.selectAttend(s_no, c_no); //수정할 attendDTO 저장
		
		if (attendDTO == null) {
			log.info("출석테이블 검색 실패!!");
		}
		else {
			//++출결상태 정하기..
			SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
			Date start= format.parse(attendDTO.getA_starttime());//출결 시작시간

			String curTime= format.format(System.currentTimeMillis());
			attendDTO.setA_time(curTime);//출석누른 시간으로 DTO set
			Date end= format.parse(curTime);//출석 누른 시간
			
			log.info("출결 시작: " + start + "출결 확인: " + end);
			
			long diff= (end.getTime() - start.getTime()) / (60 * 1000);
			log.info(diff+"분 경과");
			
			//출결 상태 설정
			int newState = 2; 
			if(diff <= 10) {//정상 출석
				newState = 0;
			}
			else {//지각
				newState = 1;
			}
			
			attendDTO.setA_state(newState);//DTO 출석상태 변경
			
			attendService.updateAttend(attendDTO);
		}
		
		
		return true;

	}
	
}
