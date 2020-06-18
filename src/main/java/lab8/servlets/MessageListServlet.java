package lab8.servlets;

import lab8.classes.ChatMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/chat/messages") // сирулет(классы поток  которые обрабатывают запросы) отображает сообщения
public class MessageListServlet extends ChatServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter printWriter = resp.getWriter();
		
		printWriter.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><meta http-equiv='refresh' content='5'></head>");
		printWriter.println("<body>");
		String avtor_pred = "";

		for (int i = Messages.size(); i >0 ; i--) //выводим сообщения по обратному

		{
			ChatMessage message = Messages.get(i);


                if (message.getWhere().equalsIgnoreCase("All") && message.getAuthor().equals(avtor_pred)) {
                    /*String probel = "";
                    for(int xex = 0; xex < message.getAuthor().length() + 1; xex++)
                    { probel += " "; }*/
                    printWriter.println("<div>" + message.getMessage() + "</div>");


                } else if(message.getWhere().equalsIgnoreCase("All")) {
                    printWriter.println("<div><strong><font color='red'>" + message.getAuthor() + "</font></strong>: " + "<font color='red'>"+message.getMessage() + "</font></div>");
                }
                else if (ActiveUsers.get(message.getWhere()).getSessionId().equals(req.getSession().getId())) {
                    printWriter.println("<div><strong>" + message.getAuthor() + " to you</strong>: " + message.getMessage() + "</div>");
                } else if (ActiveUsers.get(message.getAuthor()).getSessionId().equals(req.getSession().getId())) {
                    printWriter.println("<div><strong><font color='red'>you to " + message.getWhere() + "</font></strong>: " + message.getMessage() + "</div>");
                }


            avtor_pred = message.getAuthor();
		}
		printWriter.println("</body></html>");
	}
}
