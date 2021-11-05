package com.paas.runup.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.paas.runup.config.JwtTokenUtil;
import com.paas.runup.dto.UserDTO;
import com.paas.runup.service.JwtUserDetailsService;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailService;
    
//    @Autowired
//    private UserDAO userService;

    @ResponseBody
    
    @ApiOperation(value="선생님, 학생 - 로그인", notes="이메일과 비밀번호를 입력하여 로그인한다.(null인 경우 로그인 실패)")
	@RequestMapping(value= "/login", method= RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        
    	log.info("로그인");
    	
    	final UserDTO member = userDetailService.authenticateByEmailAndPassword
                (authenticationRequest.getEmail(), authenticationRequest.getPassword());
        if(member==null) {
        	return null;
        }
        log.info("로그인 정보: "+ member.getUser_name());
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("user_no", member.getUser_no());
        
        final String token = "Bearer "+jwtTokenUtil.generateToken(member.getUser_email(), map);
        
        return ResponseEntity.ok(new JwtResponse(token));
    }
    
//    @ApiOperation(value = "회원 - 이메일로 회원번호 조회", notes = "[아이디/비밀번호 찾기]에서 입력받은 [이메일]로 [회원번호, 회원종류(선생님/학생)]을 조회힌다.(-1은 가입된 회원 없음.)")
//	@RequestMapping(value = "/getUserByEmail/{email}", method = RequestMethod.GET)
//	public int getStudentByEmail(HttpServletRequest request, @PathVariable String email) throws Exception {
//		System.out.println("회원 정보 조회");
//		UserDTO user= userService.findByEmail(email);
//		if(s ==null) {
//			return -1;
//		}
//		return s.getS_no();
//	}
    
}

@Data
@NoArgsConstructor
@Getter
@Setter
class JwtRequest {
	
	@ApiModelProperty(name = "email", value = "이메일", example = "XXXX@gmail.com")
	private String email;
	@ApiModelProperty(name = "password", value = "비밀 번호", example = "password111")
	private String password;

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}

@Data
@AllArgsConstructor
class JwtResponse {
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}