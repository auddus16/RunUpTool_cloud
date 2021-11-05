package com.paas.runup.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class QuizCheckDTO {
//	QC_NO INT PRIMARY KEY AUTO_INCREMENT,
//	Q_NO INT NOT NULL,
//	S_NO INT NOT NULL,
//	QC_STATE BOOLEAN NOT NULL DEFAULT FALSE,
//	QC_TIME DATETIME NOT NULL DEFAULT NOW(),
//	QC_ANS VARCHAR(200) NOT NULL DEFAULT ‘답 없음.’,
//	FOREIGN KEY(Q_NO) REFERENCES QUIZ(Q_NO) ON DELETE CASCADE ON UPDATE CASCADE,
//	FOREIGN KEY(S_NO) REFERENCES STUDENT(S_NO) ON DELETE CASCADE ON UPDATE CASCADE

	@ApiModelProperty(name = "qc_no", value = "퀴즈 체크 번호",example = "1", hidden=true)
	private int qc_no;
	
	@ApiModelProperty(name = "q_no", value = "퀴즈 번호",example = "1")
	private int q_no;
	
	@ApiModelProperty(name = "s_no", value = "학생 번호",example = "1")
	private int s_no;
	
	@ApiModelProperty(name = "qc_state", value = "퀴즈 제출 여부", example = "true")
	private boolean qc_state;
	
	@ApiModelProperty(name = "qc_time", value = "퀴즈 제출 날짜",example = "2021-10-10", hidden=true)
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	private String qc_time;
	
	@ApiModelProperty(name = "qc_ans", value = "퀴즈 제출 답안", example = "apple")
	private String qc_ans;
	
	@ApiModelProperty(name = "s_name", value = "학생 이름", example = "김학생")
	private String s_name; //조인 속성 값
	
	@ApiModelProperty(name = "q_ques", value = "퀴즈 문제", example = "사과를 영어로?")
	private String q_ques; //조인 속성 값
	
	
	public String getQ_ques() {
		return q_ques;
	}
	public void setQ_ques(String q_ques) {
		this.q_ques = q_ques;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public int getQc_no() {
		return qc_no;
	}
	public void setQc_no(int qc_no) {
		this.qc_no = qc_no;
	}
	public int getQ_no() {
		return q_no;
	}
	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public boolean getQc_state() {
		return qc_state;
	}
	public void setQc_state(boolean qc_state) {
		this.qc_state = qc_state;
	}
	public String getQc_time() {
		return qc_time;
	}
	public void setQc_time(String qc_time) {
		this.qc_time = qc_time;
	}
	public String getQc_ans() {
		return qc_ans;
	}
	public void setQc_ans(String qc_ans) {
		this.qc_ans = qc_ans;
	}
	

}
