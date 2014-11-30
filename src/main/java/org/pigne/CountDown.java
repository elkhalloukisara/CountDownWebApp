package org.pigne;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountDown extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("diff", DateUtils.differenceToNow("dd/MM/yyyy HH:mm:ss", "01/12/2014 12:30:00"));
		} catch (DateUtils.DateUtilsParseException e){
			throw new ServletException(e);
		}
		request.setAttribute("author", "Yoann");
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/CountDownView.jsp")
				.forward(request, response);
	}
}