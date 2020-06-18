package lab8.servlets;

//import com.sun.deploy.uitoolkit.ui.ConsoleWindow;
import lab8.classes.ChatMessage;
import lab8.classes.ChatUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

@WebServlet("/chat/send_message")
public class NewMessageServlet extends ChatServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if (req.getParameter("message").equals("") ||
				(!ActiveUsers.containsKey(req.getParameter("person")) && !req.getParameter("person").equalsIgnoreCase("all")) ||
				req.getParameter("person").equals(req.getSession().getAttribute("username")))
		{
			resp.sendRedirect("/views/new_message.html");
			return;
		}
		
		ChatMessage message = new ChatMessage((String)req.getSession().getAttribute("username"), req.getParameter("message"), Calendar.getInstance().getTimeInMillis(), req.getParameter("person"));
		Messages.add(message);
		
		for(ChatUser user: ActiveUsers.values())
		{
			if (user.getName().equals(req.getSession().getAttribute("username")))
			{
				user.setLastInteractionTime(Calendar.getInstance().getTimeInMillis());
				break;
			}
		}
		
		resp.sendRedirect("/views/new_message.html");
	}
}
