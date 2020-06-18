package lab8.servlets;

import lab8.classes.ChatMessage;
import lab8.classes.ChatUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/start")
public class ChatServlet extends HttpServlet
{
	protected HashMap<String, ChatUser> ActiveUsers;
	protected ArrayList<ChatMessage> Messages;
	
	//Messages.add(new ChatMessage("system", "", 123, "all"));
	
	@Override
	public void init() throws ServletException
	{
		super.init();
		
		ActiveUsers = (HashMap<String, ChatUser>) getServletContext().getAttribute("ActiveUsers");
		Messages = (ArrayList<ChatMessage>) getServletContext().getAttribute("Messages");
		
		if(ActiveUsers == null)
		{
			ActiveUsers = new HashMap<String, ChatUser>();
			getServletContext().setAttribute("ActiveUsers", ActiveUsers);
		}
		
		if(Messages == null)
		{
			Messages = new ArrayList<ChatMessage>();
			getServletContext().setAttribute("Messages", Messages);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.sendRedirect("/chat/login");
	}
}
