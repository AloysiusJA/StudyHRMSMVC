package com.ja.learning.hrmsmvc.controller;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.ja.learning.hrmsmvc.exception.HrmsException;
import com.ja.learning.hrmsmvc.model.Departments;
import com.ja.learning.hrmsmvc.model.User;
import com.ja.learning.hrmsmvc.service.LoginService;
import com.ja.learning.hrmsmvc.service.LoginServiceImpl;


/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LoginService loginservice = new LoginServiceImpl();
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		PrintWriter pw = response.getWriter();
		pw.println(userName);
		pw.println(passWord);
		
		//Populate the User object
		
		User user= new User();
		user.setUserName(userName);
		user.setPassword(passWord);
		
		 List <Departments> deptList = new ArrayList<Departments>();
		
		try {
			user = loginservice.validateLogin(user);
			pw.println(user);
			if(user.isValid()) {				
				 HttpSession session = request.getSession(true);	    
		          session.setAttribute("currentSessionUser",user); 
		          deptList = loginservice.getDepartments();
					deptList.forEach(System.out::println);
		          session.setAttribute("departments", deptList);
		          
		        if(user.getRoleid()==1) {
		        	request.getRequestDispatcher("./jsp/studentHomepage.jsp").forward(request, response);
		        }
		        else if(user.getRoleid()==2) {
					request.getRequestDispatcher("/jsp/employeeHomepage.jsp").forward(request, response);
				}
		        else if(user.getRoleid()==3) {
					request.getRequestDispatcher("/jsp/adminHomepage.jsp").forward(request, response);
				}
				
		        else if(user.getRoleid()==4) {				
					
					request.getRequestDispatcher("./jsp/hrHomepage.jsp").forward(request, response);
				}
								
			}
			else {
				pw.println("Invalid Login");
				//request.getRequestDispatcher("./jsp/login.jsp").forward(request, response);
			}
			
		} catch (HrmsException e) {
			// TODO Auto-generated catch block
			pw.println(e.getErrorCode());
			e.printStackTrace();
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
