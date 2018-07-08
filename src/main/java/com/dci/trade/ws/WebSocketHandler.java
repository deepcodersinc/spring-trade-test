package com.dci.trade.ws;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

	ResponseHandler responseHandler;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// the messages will be broadcasted to all users.
		System.out.println("Connected.....");
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
		if (this.responseHandler != null) {
			this.responseHandler.handleResponse(message.getPayload());
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) 
			throws Exception {
		System.out.println("Disconnected.....");
	}

	public void addResponseHandler(ResponseHandler responseHandler) {
		this.responseHandler = responseHandler;
	}

	public static interface ResponseHandler {
		public void handleResponse(String message);
	}

}