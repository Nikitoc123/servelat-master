package lab8.classes;

public class ChatUser  // класс пользователя
{
	private String name;
	private String sessionId;
	private long LastInteractionTime;
	
	public ChatUser(String name, String sessionId, long LastInteractionTime)
	{
		this.name = name;
		this.sessionId = sessionId;
		this.LastInteractionTime = LastInteractionTime;
	}
	
	public String getName()
	{
		return name;
	}
	public String getSessionId()
	{
		return sessionId;
	}
	public long getLastInteractionTime()
	{
		return LastInteractionTime;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}
	public void setLastInteractionTime(long lastInteractionTime)
	{
		LastInteractionTime = lastInteractionTime;
	}
}
