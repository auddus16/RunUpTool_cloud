package com.paas.runup.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterDTO {
	/*
	R_NO INT PRIMARY KEY AUTO_INCREMENT,
	C_NO INT NOT NULL, 
	S_NO INT NOT NULL,
	R_TIME DATETIME NOT NULL DEFAULT NOW(),
	FOREIGN KEY(C_NO) REFERENCES CLASS(C_NO) ON DELETE CASCADE ON UPDATE CASCADE, 
	FOREIGN KEY(S_NO) REFERENCES STUDENT(S_NO) ON DELETE CASCADE ON UPDATE CASCADE
	 */
	@ApiModelProperty(name = "r_no", value = "등록 번호", example = "1", hidden=true)
	private int r_no;
	
	@ApiModelProperty(name = "c_no", value = "수업 번호", example = "1")
	private int c_no;
	
	@ApiModelProperty(name = "s_no", value = "학생 번호", example = "1")
	private int s_no;	
	
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	@ApiModelProperty(name = "r_time", value = "등록 시간", example = "yyyy-MM-dd hh:mm:ss", hidden=true)
	private String r_time;
	
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getR_time() {
		return r_time;
	}
	public void setR_time(String r_time) {
		this.r_time = r_time;
	}
	
	
}
