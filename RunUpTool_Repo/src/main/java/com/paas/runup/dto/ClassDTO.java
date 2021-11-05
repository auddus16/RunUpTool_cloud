package com.paas.runup.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClassDTO {
	/*
	C_NO INT PRIMARY KEY AUTO_INCREMENT,
	C_NAME VARCHAR(20) NOT NULL,
	C_TIME VARCHAR(20) NOT NULL,
	C_STUNUM INT NOT NULL,
	T_NO INT NOT NULL,
	FOREIGN KEY(T_NO) REFERENCES TEACHER(T_NO) ON DELETE 
CASCADE ON UPDATE CASCADE
	 */
	
	@ApiModelProperty(name = "c_no", value = "수업 번호", example = "1", hidden=true)
	private int c_no;
	
	@ApiModelProperty(name = "c_name", value = "수업 이름", example = "수학1반")
	private String c_name;
	
	@ApiModelProperty(name = "c_time", value = "수업 시간", example = "목 10:00-10:50")
	private String c_time;
	
	@ApiModelProperty(name = "c_stunum", value = "학생 수", example = "30", hidden=true)
	private int c_stunum;
	
	@ApiModelProperty(name = "t_no", value = "선생님 번호", example = "1", hidden=true)
	private int t_no;
	
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_time() {
		return c_time;
	}
	public void setC_time(String c_time) {
		this.c_time = c_time;
	}
	public int getC_stunum() {
		return c_stunum;
	}
	public void setC_stunum(int c_stunum) {
		this.c_stunum = c_stunum;
	}
	public int getT_no() {
		return t_no;
	}
	public void setT_no(int t_no) {
		this.t_no = t_no;
	}
	
	
}
