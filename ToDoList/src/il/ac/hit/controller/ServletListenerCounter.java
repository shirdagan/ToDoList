package il.ac.hit.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ServletListenerCounter implements HttpSessionListener
{
	private static int sessionCounter = 0;
	
	public static int getSessionCounter()
	{
		return sessionCounter;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se)
	{
		sessionCounter++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se)
	{
		sessionCounter--;
	}
}