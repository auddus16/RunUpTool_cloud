package com.paas.runup.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@JsonIgnoreProperties({"q_no"})

public class QuizDTO {
//	Q_NO INT PRIMARY KEY AUTO_INCREMENT,
//	C_NO INT NOT NULL,
//	Q_QUES VARCHAR(200) NOT NULL,
//	Q_ANS VARCHAR(200) NOT NULL DEFAULT ‘답 없음.’,
//	Q_TYPE BOOLEAN NOT NULL,
//	Q_TIMELIMIT TIME NOT NULL,
//	FOREIGN KEY(C_NO) REFERENCES CLASS(C_NO) ON DELETE CASCADE ON UPDATE CASCADE
	
	
	@ApiModelProperty(name = "q_no", value = "퀴즈 번호",example = "1", hidden=true)
	private int q_no;
	
	@ApiModelProperty(name = "c_no", value = "수업 번호", example = "1")
	private int c_no;
	
	@ApiModelProperty(name = "q_ques", value = "퀴즈 문제", example = "사과를 영어로?")
	private String q_ques;
	
	@ApiModelProperty(name = "q_ans", value = "퀴즈 답", example = "apple")
	private String q_ans;
	
	@ApiModelProperty(name = "q_type", value = "퀴즈 유형(True:서술형 False:단답형)", example = "0")
	private boolean q_type;
	
	@ApiModelProperty(name = "q_timelimit", value = "퀴즈 제한 시간(hh:mm:ss)", example = "00:03:00")
	private String q_timelimit;
	
	
	public int getQ_no() {
		return q_no;
	}
	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getQ_ques() {
		return q_ques;
	}
	public void setQ_ques(String q_ques) {
		this.q_ques = q_ques;
	}
	public String getQ_ans() {
		return q_ans;
	}
	public void setQ_ans(String q_ans) {
		this.q_ans = q_ans;
	}
	public boolean getQ_type() {
		return q_type;
	}
	public void setQ_type(boolean q_type) {
		this.q_type = q_type;
	}
	public String getQ_timelimit() {
		return q_timelimit;
	}
	public void setQ_timelimit(String q_timelimit) {
		this.q_timelimit = q_timelimit;
	}


}
