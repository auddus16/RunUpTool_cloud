package com.paas.runup.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class StudentDTO{
	/*
	S_NO INT PRIMARY KEY AUTO_INCREMENT,
	S_ID VARCHAR(20) NOT NULL, 
	S_NAME VARCHAR(20) NOT NULL, 
	S_BIRTH DATE NOT NULL, 
	S_GENDER BOOLEAN NOT NULL, 
	S_SCHOOL VARCHAR(20) NOT NULL, 
	S_GRADE INT NOT NULL,
	S_CLASS INT NOT NULL,
	S_PASSWORD VARCHAR(20) NOT NULL, 
	S_EMAIL VARCHAR(20) UNIQUE KEY NOT NULL
	 */
	
	@ApiModelProperty(name = "s_no", value = "학생 번호", example = "1", hidden = true)
	private int s_no;
	
	@ApiModelProperty(name = "s_id", value = "학생 아이디", example = "studentId")
	private String s_id;
	
	@ApiModelProperty(name = "s_name", value = "학생 이름", example = "김학생")
	private String s_name;
	
	@ApiModelProperty(name = "s_birth", value = "학생 생년월일", example = "YYYY-MM-DD")
	private String s_birth;
	
	@ApiModelProperty(name = "s_gender", value = "학생 성별(0:남자 1:여자)", example = "1")
	private boolean s_gender;
	
	@ApiModelProperty(name = "s_school", value = "학생 학교", example = "XXX학교")
	private String s_school;
	
	@ApiModelProperty(name = "s_grade", value = "학생 학년", example = "1")
	private int s_grade;
	
	@ApiModelProperty(name = "s_class", value = "학생 반", example = "1")
	private int s_class;
	
	@ApiModelProperty(name = "s_password", value = "학생 비밀번호", example = "studentPw")
	private String s_password;
	
	@ApiModelProperty(name = "s_email", value = "학생 이메일", example = "XXXX@gmail.com")
	private String s_email;

	
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_birth() {
		return s_birth;
	}
	public void setS_birth(String s_birth) {
		this.s_birth = s_birth;
	}
	public boolean getS_gender() {
		return s_gender;
	}
	public void setS_gender(boolean s_gender) {
		this.s_gender = s_gender;
	}
	public String getS_school() {
		return s_school;
	}
	public void setS_school(String s_school) {
		this.s_school = s_school;
	}
	public int getS_grade() {
		return s_grade;
	}
	public void setS_grade(int s_grade) {
		this.s_grade = s_grade;
	}
	public int getS_class() {
		return s_class;
	}
	public void setS_class(int s_class) {
		this.s_class = s_class;
	}
	public String getS_password() {
		return s_password;
	}
	public void setS_password(String s_password) {
		this.s_password = s_password;
	}
	public String getS_email() {
		return s_email;
	}
	public void setS_email(String s_email) {
		this.s_email = s_email;
	}
	
	
}
