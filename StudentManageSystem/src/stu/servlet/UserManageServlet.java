package stu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import stu.common.StringUtils;
import stu.dao.impl.ImplManagerDAO;
import stu.vo.SystemManagerInfo;

public class UserManageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	// 上个网页的路径
	private String uri = null;
	
	ImplManagerDAO implManager=null;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	
	uri = request.getRequestURI();
	request.setAttribute("uri", uri);
	
	int startInt = uri.lastIndexOf("/") + 1;
	int endInt = uri.lastIndexOf(".");
	String prefix = uri.substring(startInt, endInt);
	
	if (prefix.equals("userLoginDo")) {
		this.checkUserExist(request, response);
	}
}
	
	/**
	 * 检查管理员是否存在
	 */
	private void checkUserExist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	implManager=new ImplManagerDAO();
	SystemManagerInfo managerInfo = null;
	String manageName = request.getParameter("logname");
	request.getSession().setAttribute("name",manageName);
	String md5ManagePassword = request.getParameter("logpass");
	String md5RandomKey = (String)request.getSession().getAttribute("md5RandomKey");  
	String md5ManagePassword_real = "";
	String message = "";
	
	System.out.println(manageName);
	System.out.println(md5ManagePassword);
		
	managerInfo = implManager.GetManagerInfoByName(manageName);
	if(managerInfo==null)
		return ;
	md5ManagePassword_real = managerInfo.getManagePassw()+md5RandomKey;
	if(md5ManagePassword_real.equals(md5ManagePassword))
	{
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
	else
	{
		message="登录失败";
		request.getSession().setAttribute("message", message);
	}
	}
}
