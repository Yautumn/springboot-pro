package com.yautumn.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest extends HttpServlet{
	private static final long serialVersionUID = -3937269858299287310L;

	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
		doPost(req,resp);
	}
	
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.getWriter().write("自定义servlet");
	}
}
