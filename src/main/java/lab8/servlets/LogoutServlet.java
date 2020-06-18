package lab8.servlets;

import lab8.classes.ChatMessage;
import lab8.classes.ChatUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import java.util.Calendar;

@WebServlet("/chat/logout")
public class LogoutServlet extends ChatServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
        //ChatUser Xex = new ChatUser("SHTOBLIN", "0", 0);
		/*ChatMessage message = new ChatMessage((String)req.getSession().getAttribute("username"),
				" покинул чат", Calendar.getInstance().getTimeInMillis(), req.getParameter("person"));
		Messages.add(message);
		req.getSession().setAttribute("message", " вышел из чата");
		resp.sendRedirect("/chat/send_message");*/

        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html><body>");
        printWriter.println("<div><i>" + "Пользователь вышел из себя" + "</i></div>");
        printWriter.println("</body></html>");
		Cookie sess = new Cookie("SessionIdCook", null);
		sess.setMaxAge(0);
		resp.addCookie(sess);
	
		ActiveUsers.remove(req.getSession().getAttribute("username"));
		req.getSession().setAttribute("username", null);
		resp.sendRedirect("/chat/login");
	}
}
