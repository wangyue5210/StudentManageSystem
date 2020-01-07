package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.group.Response;

import com.sun.org.apache.bcel.internal.generic.NEW;

import dao.impl.StudentDaoImpl;
import domain.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;
import util.PageUtil;
import util.ServiceFactory;


public class StudentController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入到学生操作");
		StudentService studentService=(StudentService) ServiceFactory.getService(new StudentServiceImpl());
		
		//取得请求路径url-pattern
		String path= request.getServletPath();
		if ("/student/list.do".equals(path)) {
			list(request,response,studentService);
			
		}else if ("/student/save.do".equals(path)) {
			
			save(request,response,studentService);
			
		}else if ("/student/edit.do".equals(path)) {
			
			edit(request,response,studentService);
			
		}else if ("/student/update.do".equals(path)) {
			
			update(request,response,studentService);
			
		}else if ("/student/delete.do".equals(path)) {
			
			delete(request,response,studentService);
		}
		
		
	}

	



	private void list(HttpServletRequest request, HttpServletResponse response, StudentService studentService)throws ServletException, IOException {
		
		System.out.println("进入到查询列表操作");
		response.setContentType("text/html;charset=utf-8");
		String pageNoStr=request.getParameter("pageNo");
		Map<String, Object> map = studentService.getAll(pageNoStr);
		PageUtil pu = (PageUtil) map.get("pu");
		List<Student> sList=(List<Student>) map.get("sList");
		
		
		
		// {"pu":{"pageNo":?,"pageCount":?,"pageSize":?,"total":?},"sList":[{"id":"?","name":"?","age":?},{},{}]}
		StringBuffer buf=new StringBuffer();
		buf.append("{\"pu\":{\"pageNo\":"+
		pu.getPageNo()+",\"pageCount\":"+
				pu.getPageCount()+",\"pageSize\":"+pu.getPageSize()+
				",\"total\":"+pu.getTotal()+
				"},\"sList\":[");
		
		for(int i =0;i<sList.size();i++) {
			Student student =sList.get(i);
			//buf.append("{\"id\":\""+student.getId()+"\",\"name\":\""+student.getName()+"\",\"age\":"+student.getAge()+"}");
			
			buf.append("{\"id\":\"");
			buf.append(student.getId());
			buf.append("\",\"name\":\"");
			buf.append(student.getName());
			buf.append("\",\"age\":");
			buf.append(student.getAge());
			buf.append("}");
			if (i<sList.size()-1) {
				buf.append(",");
			}
			
		}
		
		buf.append("]}");
		System.out.println(buf.toString());
		PrintWriter out=response.getWriter();
		out.print(buf.toString());
		out.close();
		
	}





	private void save(HttpServletRequest request, HttpServletResponse response, StudentService studentService)throws ServletException, IOException {
		System.out.println("进入到添加操作");
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		//System.out.println(name);
		String agestr=request.getParameter("age");
		int age=Integer.valueOf(agestr);
		Student student=new Student();
		student.setName(name);
		student.setAge(age);
		studentService.save(student);//不需要返回值
		response.sendRedirect(request.getContextPath()+"/jsp/student/index.jsp");
	}





	private void edit(HttpServletRequest request, HttpServletResponse response, StudentService studentService)throws ServletException, IOException {
		System.out.println("进入到根据id查询单条操作");
		String id=request.getParameter("id");
		Student student=studentService.edit(id);
		//翻译成json串返回到前端
		String str="{\"id\":\""+student.getId()+"\",\"name\":\""+student.getName()+"\",\"age\":"+student.getAge()+"}";
		System.out.println(str);
		System.out.println("666");
		PrintWriter out=response.getWriter();
		out.print(str);
		out.close();
	}





	private void update(HttpServletRequest request, HttpServletResponse response, StudentService studentService)throws ServletException, IOException {
		System.out.println("进入到修改操作");
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		String agestr=request.getParameter("age");
		int age=Integer.valueOf(agestr);
		Student student=new Student();
		student.setName(name);
		student.setAge(age);
		student.setId(id);
		studentService.update(student);//不需要返回值
		response.sendRedirect(request.getContextPath()+"/jsp/student/index.jsp");
	}





	private void delete(HttpServletRequest request, HttpServletResponse response, StudentService studentService)throws ServletException, IOException {
		System.out.println("进入到删除操作");
		String id=request.getParameter("id");
		studentService.delete(id);
		response.sendRedirect(request.getContextPath()+"/jsp/student/index.jsp");
	}





	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
