package com.yatin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello", 
			loadOnStartup = 1, 
			initParams = { 
				@WebInitParam(name = "first_init_param_name", value = "first_init_param_value"),
				@WebInitParam(name = "second_init_param_name", value = "second_init_param_value")
			})
public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 8971101587096045191L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String servletContextParam = req.getServletContext().getInitParameter("first_context_param_name");
		String servletConfigParamOne = getInitParameter("first_init_param_name");
		String servletConfigParamTwo = getInitParameter("second_init_param_name");

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.print("<html><body>");
		out.println("<b>Hello World</b>");
		out.println("<p>The servlet Context Param value : " + servletContextParam + "</p>");
		out.println("<p>The servlet Config param values : " + servletConfigParamOne + ", " + servletConfigParamTwo + "</p>");
		out.print("</body></html>");
	}
}
