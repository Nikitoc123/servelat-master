package lab8.servlets;

import lab8.classes.ChatMessage;
import lab8.classes.ChatUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/chat/users")
public class UsersServlet extends ChatServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter printWriter = resp.getWriter();
		
		printWriter.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><meta http-equiv='refresh' content='5'></head>");
		printWriter.println("<body>");
		
		for(ChatUser user: ActiveUsers.values())
		{
			printWriter.println("<div style=\"text-align: center;\"><strong>" + user.getName() + "</strong>");
		}
		
		printWriter.println("</body></html>");
	}
}
