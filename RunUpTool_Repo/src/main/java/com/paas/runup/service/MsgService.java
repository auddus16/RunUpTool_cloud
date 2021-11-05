package com.paas.runup.service;

import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paas.runup.dao.MsgRoomDAO;
import com.paas.runup.dto.MsgRoom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@MapperScan(basePackages="com.paas.runup.dao")
public class MsgService implements MsgRoomDAO{
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MsgRoomDAO msgRoomDAO;
	
	//private Map<String, MsgRoom> msgRooms;
	
//	@PostConstruct
//	private void init() {
//		System.out.println("init 실행됨");
//		msgRooms = new LinkedHashMap<>();
//	}
	
//	public List<MsgRoom> findAllRoom(){
//		System.out.println("findAllRoom 실행됨");
//		System.out.println(msgRooms.values());
//		return new ArrayList<>(msgRooms.values());
//	}
//	
//	public MsgRoom findbyId(String roomId) {
//		System.out.println("findbyId 실행됨"+roomId);
//		return msgRooms.get(roomId);
//	}
	
//	public MsgRoom createRoom(String name) {
//		String roomId= name;
//		System.out.println("새로운 룸 생성 :"+roomId);
//		
//		MsgRoom msgRoom = new MsgRoom(roomId);
//		msgRooms.put(roomId, msgRoom);
//		
//		return msgRoom;
//		//return MsgRoom.builder().roomId(roomId).build();
//	}
	
	public <T> void sendMessage(WebSocketSession session, T message) {
		try {
			session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int insertMsgRoom(MsgRoom msgRoom) {
		// TODO Auto-generated method stub
		log.info("새로운 룸 생성 :"+msgRoom.getRoomId());
		
		return msgRoomDAO.insertMsgRoom(msgRoom);
	}

	@Override
	public int deleteMsgRoom(String roomId) {
		// TODO Auto-generated method stub
		return msgRoomDAO.deleteMsgRoom(roomId);
	}

	@Override
	public MsgRoom selectMsgRoomId(String roomId) {
		// TODO Auto-generated method stub
		return msgRoomDAO.selectMsgRoomId(roomId);
	}

	@Override
	public MsgRoom selectRoomId(int c_no) {
		// TODO Auto-generated method stub
		return msgRoomDAO.selectRoomId(c_no);
	}
}
