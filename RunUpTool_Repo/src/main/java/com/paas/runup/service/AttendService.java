package com.paas.runup.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paas.runup.dao.AttendDAO;
import com.paas.runup.dto.AttendDTO;

@Service
@MapperScan(basePackages="com.paas.runup.dao")
public class AttendService implements AttendDAO{
	
	@Autowired
	AttendDAO attendDAO;
	
	@Override
	public List<AttendDTO> selectAttendByDate(Map<String, Object> hm) throws Exception {
		// TODO Auto-generated method stub
		return attendDAO.selectAttendByDate(hm);
	}

	@Override
	public AttendDTO selectAttend(int s_no, int c_no) throws Exception {
		// TODO Auto-generated method stub
		return attendDAO.selectAttend(s_no, c_no);
	}

	@Override
	public int updateAttend(AttendDTO a) throws Exception {
		// TODO Auto-generated method stub
		return attendDAO.updateAttend(a);
	}

	@Override
	public int updateAttendTime(int a_no) throws Exception {
		// TODO Auto-generated method stub
		return attendDAO.updateAttendTime(a_no);
	}

	@Override
	public int updateAttendState(int a_no, int a_state) throws Exception {
		// TODO Auto-generated method stub
		return attendDAO.updateAttendState(a_no, a_state);
	}

	@Override
	public int deleteAttend(int a_no) throws Exception {
		// TODO Auto-generated method stub
		return attendDAO.deleteAttend(a_no);
	}

	@Override
	public int insertAttend(int s_no, int c_no) throws Exception {
		// TODO Auto-generated method stub
		return attendDAO.insertAttend(s_no, c_no);
	}

	@Override
	public List<AttendDTO> selectAttendList(int s_no, int c_no) throws Exception {
		// TODO Auto-generated method stub
		return attendDAO.selectAttendList(s_no, c_no);
	}

}