package lab8.servlets;

import lab8.classes.ChatMessage;
import lab8.classes.ChatUser;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet("/chat/login")
public class LoginServlet extends ChatServlet
{
	private int SessionTimeout = 3600;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter printWriter = resp.getWriter();
		String errmsg = (String)req.getSession().getAttribute("error");
		String name = (String)req.getSession().getAttribute("username");
		String ID = null;
		
		if (name == null)
		{
			for (Cookie cookie:req.getCookies())
			{
				if (cookie.getName().equals("SessionIdCook"))
				{
					ID = cookie.getValue();
					cookie.setValue(req.getSession().getId());
					break;
				}
			}
			
			if (ID != null)
			{
				for(ChatUser user: ActiveUsers.values())
				{
					if (user.getSessionId().equals(ID))
					{
						name = user.getName();
						break;
					}
				}
			}
		}
		
		if (name != null && !"".equals(name))
			errmsg = LogonAttempt(name, req, resp);
		
		if (errmsg == null)
			errmsg = "";
		
		printWriter.println("<html lang=\"en\">");
		printWriter.println("<head>");
		printWriter.println("<meta charset=\"UTF-8\">\n" +
				"<title>Login</title>");
		printWriter.println("</head>");
		printWriter.println("<body>");
		printWriter.println("<form method=\"post\">\n" +
				"<label>Name: (less than 10 characters)<br>\n" +
				"<input maxlength=\"9\" type=\"text\" autocomplete=\"off\" name=\"name\"><br />\n" +
				"</label><br>\n" +
				"<button type=\"submit\">Submit</button>\n" +
				"</form>" +
				errmsg);
		printWriter.println("</body>");
		printWriter.println("</html> ");
		req.getSession().setAttribute("error", null);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String name = req.getParameter("name");
		String errmsg = LogonAttempt(name, req, resp);
		
		if (errmsg != null)
		{
			req.getSession().setAttribute("error", errmsg);
			resp.sendRedirect("/chat/login");
		}
	}
	
	private String LogonAttempt(String name, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		String ID = req.getSession().getId();
		ChatUser user = ActiveUsers.get(name);
		
		if (name.equalsIgnoreCase("system"))
			return "no, you're not a system";
		
		if (name.equalsIgnoreCase("all"))
			return "this name taken by a system";
		
		if (user == null)
		{
			user = new ChatUser(name, ID, Calendar.getInstance().getTimeInMillis());
			ActiveUsers.put(user.getName(), user);
		}
		
		if (user.getSessionId().equals(ID) || user.getLastInteractionTime() < Calendar.getInstance().getTimeInMillis() - SessionTimeout * 1000)
		{
			req.getSession().setAttribute("username", name);
			
			user.setLastInteractionTime(Calendar.getInstance().getTimeInMillis());
			user.setSessionId(ID);
			
			Cookie sess = new Cookie("SessionIdCook", req.getSession().getId());
			sess.setMaxAge(60 * 60 * 24 * 365);
			resp.addCookie(sess);
			resp.sendRedirect("/views/chat_view.html");
			return null;
		}
		else
		{
			return "name taken";
		}
	}
}
