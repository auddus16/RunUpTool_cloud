package com.paas.runup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.paas.runup.dto.MsgRoom;
@Mapper
@Repository
public interface MsgRoomDAO {
	int insertMsgRoom(MsgRoom msgRoom);
	
	int deleteMsgRoom(String roomId);
	
	MsgRoom selectMsgRoomId(String roomId);
	
	MsgRoom selectRoomId(int c_no);
}
