package com.paas.runup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

//    @Override
//    // connection을 맺을때 CORS 허용
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/").setAllowedOrigins("*").withSockJS();
//    }
	
	@Autowired
	private WebSocketHandler webSocketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addHandler(webSocketHandler, "/websocket").setAllowedOrigins("*").withSockJS();
	}

	public WebSocketConfig(WebSocketHandler webSocketHandler) {
		super();
		this.webSocketHandler = webSocketHandler;
	}
	
	
}