package com.paas.runup.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AttendDTO{
	/*
	A_NO INT PRIMARY KEY AUTO_INCREMENT,
	S_NO INT NOT NULL,
	C_NO INT NOT NULL,
	A_STATE INT NOT NULL DEFAULT 2,
	A_STARTTIME NOT NULL DEFAULT NOW(),
	A_TIME DATETIME NOT NULL DEFAULT NOW(),
	FOREIGN KEY(C_NO) REFERENCES CLASS(C_NO) ON DELETE CASCADE ON UPDATE CASCADE, 
	FOREIGN KEY(S_NO) REFERENCES STUDENT(S_NO) ON DELETE CASCADE ON UPDATE CASCADE
	 */
	@ApiModelProperty(name = "a_no", value = "출결 번호", example = "1", hidden=true)
	private int a_no;
	
	@ApiModelProperty(name = "s_no", value = "학생 번호", example = "1")
	private int s_no;
	
	@ApiModelProperty(name = "c_no", value = "수업 번호", example = "1")
	private int c_no;
	
	@ApiModelProperty(name = "a_state", value = "출결 상태(0:출석 1:지각 2:결석)", example = "1")
	private int a_state;
	
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	@ApiModelProperty(name = "a_starttime", value = "출석 시작시간", example = "yyyy-MM-dd hh:mm:ss", hidden=true)
	private String a_starttime;
	
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	@ApiModelProperty(name = "a_time", value = "출석 시간", example = "yyyy-MM-dd hh:mm:ss", hidden=true)
	private String a_time;
	
	@ApiModelProperty(name = "s_name", value = "학생 이름", example = "김학생")
	private String s_name; //조인 속성 값
	
	public String getS_name() {
		return s_name;
	}
	public void setStudent(String s_name) {
		this.s_name = s_name;
	}
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public int getA_state() {
		return a_state;
	}
	public void setA_state(int a_state) {
		this.a_state = a_state;
	}
	public String getA_starttime() {
		return a_starttime;
	}
	public void setA_starttime(String a_starttime) {
		this.a_starttime = a_starttime;
	}
	public String getA_time() {
		return a_time;
	}
	public void setA_time(String a_time) {
		this.a_time = a_time;
	}
	
}
