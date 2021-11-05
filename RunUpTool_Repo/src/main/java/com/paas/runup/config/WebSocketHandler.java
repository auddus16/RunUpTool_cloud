package com.paas.runup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paas.runup.dto.Message;
import com.paas.runup.dto.MsgRoom;
import com.paas.runup.service.MsgService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
	
	@Autowired
	private MsgService msgService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("payload:{}", payload);

		Message msg = objectMapper.readValue(payload, Message.class);
		MsgRoom room = msgService.selectMsgRoomId(msg.getRoomId());
		
		room.handleActions(session, msg, msgService);
	}
}
