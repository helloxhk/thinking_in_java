package com.xhk.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xhk
 * @time 2018-09-19 14:53
 */
@WebServlet(
		description = "Login Servlet",
		urlPatterns = {"/loginServlet"},
		initParams = {
				@WebInitParam(name = "user", value = "user"),
				@WebInitParam(name = "password", value = "password")
		}
)
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -1221307108052566343L;

	@Override
	public void init() throws ServletException {
		if(getServletContext().getInitParameter("dbUrl").equals("jdbc:mysql://localhost/mysql_db") &&
				getServletContext().getInitParameter("user").equals("user") &&
				getServletContext().getInitParameter("password").equals("password"))
			getServletContext().setAttribute("db_success",true);
		else
			throw new ServletException("db connection error");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String password = request.getParameter("password");

		String user1 = getServletConfig().getInitParameter("user");
		String password1 = getServletConfig().getInitParameter("password");

		log("USER:" + user + ",PASSWORD:" + password);

		if(user1.equals(user) && password1.equals(password)){
			response.sendRedirect("loginSuccess.jsp");
		} else {
			response.getWriter().println("<font color=red>Ether user or password is wrong.</font>");
			request.getRequestDispatcher("/login.html").include(request,response);
		}
	}
}
