package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import service.UserService;
import service.impl.UserServiceImpl;
import util.ServiceFactory;


public class UserController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入到用户管理");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		UserService userService=(UserService) ServiceFactory.getService(new UserServiceImpl());
		//调用业务层，来实现登录业务
		boolean flag=userService.login(username,password);
		
		if (flag) {
			request.getSession().setAttribute("username", username);
			response.sendRedirect(request.getContextPath()+"/jsp/student/index.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
