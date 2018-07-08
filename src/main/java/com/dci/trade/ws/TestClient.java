package com.dci.trade.ws;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

public class TestClient {

	static String authorizationHeader = "Bearer "
			+ "eyJhbGciOiJIUzI1NiJ9.eyJyZWZyZXNoYWJsZSI6ZmFsc2UsInN1YiI6ImJiMGNkYTJiLWE"
			+ "xMGUtNGVkMy1hZDVhLTBmODJiNGMxNTJjNCIsImF1ZCI6ImJldGEuZ2V0YnV4LmNvbSIsInN"
			+ "jcCI6WyJhcHA6bG9naW4iLCJydGY6bG9naW4iXSwiZXhwIjoxODIwODQ5Mjc5LCJpYXQiOjE"
			+ "1MDU0ODkyNzksImp0aSI6ImI3MzlmYjgwLTM1NzUtNGIwMS04NzUxLTMzZDFhNGRjOGY5MiI"
			+ "sImNpZCI6Ijg0NzM2MjI5MzkifQ.M5oANIi2nBtSfIfhyUMqJnex-JYg6Sm92KPYaUL9GKg";

	public static void main(String[] args) {
		WebSocketHandler wsHandler = new WebSocketHandler();
		WebSocketClient wsClient = new StandardWebSocketClient();
		WebSocketHttpHeaders header = new WebSocketHttpHeaders();

		header.add("Authorization", authorizationHeader);
		header.add("Accept-Language", "nl-NL,en;q=0.8");
		header.add("Content-Type", "application/json");
		header.add("Accept", "application/json");
		
		try {
			wsHandler.addResponseHandler(new WebSocketHandler.ResponseHandler() {
				@Override
				public void handleResponse(String response) {
					System.out.println(response);
				}
			});
			
			wsClient.doHandshake(wsHandler, header, new URI("ws://localhost:8080/subscriptions/me"));
						
			Thread.sleep(1000);

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
