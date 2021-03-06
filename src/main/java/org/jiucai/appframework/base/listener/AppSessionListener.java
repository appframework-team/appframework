package org.jiucai.appframework.base.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener("a listener to save visitor count")
public class AppSessionListener extends AbstractBaseListener implements HttpSessionListener {
	
	private static Long sessionCount = 0L;
	
	
	protected static Map<String, Object> sessionMap = new HashMap<String, Object>(); // 存放session的集合类
	
	public AppSessionListener() {
		log.info("AppSessionListener inited.");
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		sessionCount++;
		
		HttpSession session = event.getSession();
		String sessionId = session.getId();

		log.debug("Create new session: " + sessionId);

		sessionMap.put(sessionId, session);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		if(sessionCount > 0){
			sessionCount--;
		}
	
		HttpSession session = event.getSession();
		String sessionId = session.getId();
		sessionMap.remove(sessionId);// 利用会话ID标示特定会话
		
		log.debug("Destroy the session: " + sessionId);

	}

	public static Long getSessionCount() {
		return sessionCount;
	}

	public static Map<String, Object> getSessionMap() {
		return sessionMap;
	}

}
