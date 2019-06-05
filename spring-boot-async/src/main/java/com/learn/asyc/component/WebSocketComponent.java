package com.learn.asyc.component;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/test/info")
public class WebSocketComponent {

	private Session session;

	private static CopyOnWriteArraySet<WebSocketComponent> webSocketSet = new CopyOnWriteArraySet<WebSocketComponent>();

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);
		System.out.println("new Session be added:" + session.getId());
	}

	@OnMessage
	public void onMessage(String content, Session session) {
		System.out.println(String.format("Session->[{%s}]:Message:[{%s}]", session.getId(), content));
	}

	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("error");
	}

	@OnClose
	public void onClose() {
		System.out.println("new Session be remove:" + this.session.getId());
		webSocketSet.remove(this);
	}

	public Session getSession() {
		return session;
	}

	public static void sendMessage(String content) throws IOException {
		Iterator<WebSocketComponent> iterator = webSocketSet.iterator();
		while (iterator.hasNext()) {
			WebSocketComponent component = iterator.next();
			Session session = component.getSession();
			session.getBasicRemote().sendText(content);
		}
	}

}
