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
		String diff = "O_o";
		try {
			 diff = DateUtils.differenceFromNow("dd/MM/yyyy HH:mm:ss", "04/11/2015 17:30:00");
		} catch (DateUtils.DateUtilsParseException e){
			throw new ServletException(e);
		}
		request.setAttribute("diff", diff);
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/CountDownView.jsp")
				.forward(request, response);
	}
}