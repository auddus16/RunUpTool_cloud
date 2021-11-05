package com.paas.runup.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import com.paas.runup.service.MsgService;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@Builder
public class MsgRoom {
	/**
	CREATE TABLE MSGROOM(
    ROOMID VARCHAR(10) PRIMARY KEY
	);
	 */
	//c_no를 String으로 변환하여 roomId로 사용한다.
	private String roomId;
	
	//DB에 저장할 필요 없음.
	private Set<WebSocketSession> sessions= new HashSet<>();
	
	@Builder
	public MsgRoom(String roomId) {
		this.setRoomId(roomId);
	}
	
	public Set<WebSocketSession> getSessions() {
		return sessions;
	}

	public void setSessions(Set<WebSocketSession> sessions) {
		this.sessions = sessions;
	}

	public void handleActions(WebSocketSession session, Message message, MsgService msgService) {
		if(message.getMessageType().equals(Message.MessageType.ENTER)) {
			sessions.add(session);
			message.setMessage(message.getSender()+"님이 입장했습니다.");
			
		}
		sendMessage(message, msgService);
	}
	
	public <T> void sendMessage(T message, MsgService messageService) {
		sessions.parallelStream().forEach(session->messageService.sendMessage(session, message));
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	

}
