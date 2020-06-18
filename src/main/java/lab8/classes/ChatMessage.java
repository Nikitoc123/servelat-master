package lab8.classes;

public class ChatMessage // класс сообщения
{
	private String Author;
	private String Message;
	private long time;
	private String Where;
	
	public ChatMessage(String Author, String Message, long time, String Where)
	{
		this.Author = Author;
		this.Message = Message;
		this.time = time;
		this.Where = Where;
	}
	
	public String getAuthor()
	{
		return Author;
	}
	public String getMessage()
	{
		return Message;
	}
	public long getTime()
	{
		return time;
	}
	public String getWhere()
	{
		return Where;
	}
	
	public void setAuthor(String author)
	{
		Author = author;
	}
	public void setMessage(String message)
	{
		Message = message;
	}
	public void setTime(long time)
	{
		this.time = time;
	}
	public void setWhere(String where)
	{
		Where = where;
	}
	
	@Override
	public String toString()
	{
		return getAuthor() + " (" + getTime() + ") -> " + getMessage();
	}
}
