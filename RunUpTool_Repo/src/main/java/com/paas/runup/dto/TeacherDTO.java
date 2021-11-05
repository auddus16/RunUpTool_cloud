package com.paas.runup.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TeacherDTO {
//	T_NO INT PRIMARY KEY AUTO_INCREMENT,
//	T_ID VARCHAR(20) NOT NULL,
//	T_NAME VARCHAR(20) NOT NULL,
//	T_BIRTH DATE NOT NULL,
//	T_GENDER BOOLEAN NOT NULL,
//	T_SCHOOL VARCHAR(20) NOT NULL,
//	T_PASSWORD VARCHAR(20) NOT NULL,
//	T_EMAIL VARCHAR(20) NOT NULL
	
	@ApiModelProperty(name = "t_no", value = "선생님 번호", example = "1", hidden=true)
	private int t_no; 
	
	@ApiModelProperty(name = "t_id", value = "선생님 아이디", example = "teacherId")
	private String t_id;
	
	@ApiModelProperty(name = "t_name", value = "선생님 이름", example = "김선생")
	private String t_name;
	
	@ApiModelProperty(name = "t_birth", value = "선생님 생년월일", example = "YYYY-MM-DD")
	private String t_birth;
	
	@ApiModelProperty(name = "t_gender", value = "선생님 성별(0:남자 1:여자)", example = "1")
	private boolean t_gender;
	
	@ApiModelProperty(name = "t_school", value = "선생님 소속 학교", example = "XX학교")
	private String t_school;
	
	@ApiModelProperty(name = "t_password", value = "선생님 비밀번호", example = "teacherPw")
	private String t_password;
	
	@ApiModelProperty(name = "t_email", value = "선생님 이메일", example = "XXXX@gmail.com")
	private String t_email;
	
	public int getT_no() {
		return t_no;
	}
	public void setT_no(int t_no) {
		this.t_no = t_no;
	}
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_birth() {
		return t_birth;
	}
	public boolean isT_gender() {
		return t_gender;
	}
	public void setT_gender(boolean t_gender) {
		this.t_gender = t_gender;
	}
	public String getT_school() {
		return t_school;
	}
	public void setT_school(String t_school) {
		this.t_school = t_school;
	}
	public String getT_password() {
		return t_password;
	}
	public void setT_password(String t_password) {
		this.t_password = t_password;
	}
	public String getT_email() {
		return t_email;
	}
}
