package com.yatin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 8971101587096045191L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String servletContextParam = req.getServletContext().getInitParameter("first_context_param_name");
		String servletConfigParam = getInitParameter("first_init_param_name");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.print("<html><body>");
		out.println("<b>Hello World</b>");
		out.println("<b>The servlet Context Param value : " + servletContextParam + "</b>");
		out.println("<b>The servlet Config param value : " + servletConfigParam + "</b>");
		out.print("</body></html>");
	}
}
