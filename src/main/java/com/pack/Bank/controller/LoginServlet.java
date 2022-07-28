package com.pack.Bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pack.Bank.dao.AccountDao;
import com.pack.Bank.dao.CorporateDao;
import com.pack.Bank.dao.TransDao;
import com.pack.Bank.dao.UserDao;
import com.pack.Bank.model.Account;
import com.pack.Bank.model.Corporates;
import com.pack.Bank.model.Transaction;
import com.pack.Bank.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	UserDao udao = new UserDao();
	AccountDao acdao = new AccountDao();
	TransDao transdao = new TransDao();
	CorporateDao corpdao = new CorporateDao();
	UserDao userdao = new UserDao();
	
	public String  email;
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		System.out.println(action);
		switch(action)
		{
		case "login":
			login(request,response);
		    break;
		case "changepassword":
			changePassword(request,response);
			break;
		case "transadd":
			transadd(request,response);
			break;
		case "logout":
			logout(request,response);
			break;
		case "corpdelete":
			corpdelete(request,response);
			break;
		case "corpupdate":
			corpupdate(request,response);
			break;
		case "corpadd":
			corpadd(request,response);
			break;
		case "userdelete":
			userdelete(request,response);
			break;
		case "useradd":
			useradd(request,response);
			break;
		case "userupdate":
			userupdate(request,response);
			break;
		case "accountadd":
			accountadd(request,response);
			break;
		case "accountdelete":
			accountdelete(request,response);
			break;
		}
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
		  email = request.getParameter("email");
		  HttpSession session = request.getSession();
	      session.setAttribute("email", email);
		  String password = request.getParameter("password");
		  System.out.println(email+" "+password);
		  if(email.equals("alanwalker@smartbank.in") && password.equals("alan@backoffice123")) {
			  response.sendRedirect("backoffice.jsp");
		  }
		  else{
			  int count = udao.getUserByEmail(email,password);
			  if(count>0) {
				 int status = udao.getChangedPasswordStatus(email);
				 String name=udao.getUsername(email);
				 session.setAttribute("name", name);
				 if(status==0)
					 response.sendRedirect("frontoffice.jsp");
				 else
					 response.sendRedirect("userhome.jsp");
			  }
			  else {
				  out.println("<script type=\"text/javascript\">");
				   out.println("alert('User or password incorrect');");
				   out.println("location='login.jsp';");
				   out.println("</script>");
			  }
		  }  
	}
	protected void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
		  String password1 = request.getParameter("password1");
		  String password2 = request.getParameter("password2");
		  System.out.println(password1+" "+password2);
		  if(password1.equals(password2)) {
			  int status = udao.changePassword(email,password1);
			  if(status>0) {
				  String name=udao.getUsername(email);
				  HttpSession session = request.getSession();
				  session.setAttribute("name", name);
				  response.sendRedirect("userhome.jsp");
			  }
			  else {
				   out.println("<script type=\"text/javascript\">");
				   out.println("alert('Oops some error occured. Please try again!!');");
				   out.println("location='frontoffice.jsp';");
				   out.println("</script>");
			  }
		  }
		  else {
			   out.println("<script type=\"text/javascript\">");
			   out.println("alert('Both passwords should be same');");
			   out.println("location='frontoffice.jsp';");
			   out.println("</script>");
		  }
	}
	protected void transadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  Transaction trans = new Transaction();
		  PrintWriter out = response.getWriter();
		  trans.setAccountNumber(request.getParameter("acNo"));
		  trans.setTransactionType(request.getParameter("transType"));
		  trans.setTransactionDate(request.getParameter("transDate"));
		  trans.setBalance(request.getParameter("balance"));
		  int i=transdao.save(trans);
			if(i>0){
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Added Successfully');");
				out.println("location='userhome.jsp';");
				out.println("</script>");
			}  
			else{ 
				 out.println("<script type=\"text/javascript\">");
				 out.println("alert('Oops transaction not added try again!!');");
				 out.println("location='userhome.jsp';");
				 out.println("</script>");
			}
		  
	   }
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
	    response.sendRedirect("login.jsp");
	    System.out.println("logged out");
	}
	protected void corpadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  Corporates corp = new Corporates();
		  PrintWriter out = response.getWriter();
		  corp.setCorporateName(request.getParameter("corpName"));
		  corp.setAddress(request.getParameter("corpAddress"));
		  corp.setContactNumber(request.getParameter("corpPh"));
		  int i=corpdao.save(corp);
			if(i>0){
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Added Successfully');");
				out.println("location='corporate.jsp';");
				out.println("</script>");
			}  
			else{ 
				 out.println("<script type=\"text/javascript\">");
				 out.println("alert('Oops corpoarte not added try again!!');");
				 out.println("location='corporate.jsp';");
				 out.println("</script>");
			}
	}
	protected void corpupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  Corporates corp=new Corporates();
		  PrintWriter out = response.getWriter();
		  System.out.println("in update of servlet");
		  corp.setCorporateId(Integer.parseInt(request.getParameter("corpId")));
		  corp.setCorporateName(request.getParameter("corpName"));
		  corp.setAddress(request.getParameter("corpAddress"));
		  corp.setContactNumber(request.getParameter("corpPh"));
		  
		  System.out.println("in servlet "+corp.getCorporateId()+" "+corp.getCorporateName()+" "+corp.getAddress());
		  int i=corpdao.update(corp); 
		  if(i>0){
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Updated Successfully');");
				out.println("location='corporate.jsp';");
				out.println("</script>");
			}  
			else{ 
				 out.println("<script type=\"text/javascript\">");
				 out.println("alert('Oops corporate not updated try again!!');");
				 out.println("location='corporateupdate.jsp';");
				 out.println("</script>");
			}
	}	
	protected void corpdelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am here in delete");
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		PrintWriter out = response.getWriter();
		int i=corpdao.delete(id);
		if(i>0){
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Deleted Successfully');");
			out.println("location='corporate.jsp';");
			out.println("</script>");
		}  
		else{ 
			 out.println("<script type=\"text/javascript\">");
			 out.println("alert('Oops couldn't delete try again!!');");
			 out.println("location='corporateupdate.jsp';");
			 out.println("</script>");
		}
	}
	protected void useradd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  User user = new User();
		  PrintWriter out = response.getWriter();
		  user.setCorporateId(Integer.parseInt(request.getParameter("corpId")));
		  user.setLoginId(request.getParameter("loginId"));
		  user.setPassword("userC##");
		  user.setUserName(request.getParameter("username"));
		  user.setDepartment(request.getParameter("department"));
		  user.setAddress(request.getParameter("corpAddress"));
		  user.setContactNumber(request.getParameter("corpPh"));
		  int i=userdao.save(user);
			if(i>0){
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Added Successfully');");
				out.println("location='user.jsp';");
				out.println("</script>");
			}  
			else{ 
				 out.println("<script type=\"text/javascript\">");
				 out.println("alert('Oops user not added try again!!');");
				 out.println("location='user.jsp';");
				 out.println("</script>");
			}
	}
	protected void userupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  System.out.println("in update of servlet");  
		  User user = new User();
		  PrintWriter out = response.getWriter();
		  user.setCorporateId(Integer.parseInt(request.getParameter("corpId")));
		  user.setLoginId(request.getParameter("loginId"));
		  user.setUserName(request.getParameter("username"));
		  user.setDepartment(request.getParameter("department"));
		  user.setAddress(request.getParameter("corpAddress"));
		  user.setContactNumber(request.getParameter("corpPh"));
		  
		  System.out.println("in servlet "+user.getCorporateId()+" "+user.getUserName()+" "+user.getAddress());
		  int i=userdao.update(user); 
		  if(i>0){
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Updated Successfully');");
				out.println("location='user.jsp';");
				out.println("</script>");
			}  
			else{ 
				 out.println("<script type=\"text/javascript\">");
				 out.println("alert('Oops user not updated try again!!');");
				 out.println("location='user.jsp';");
				 out.println("</script>");
			}
	}	
	protected void userdelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String id=request.getParameter("id") ;
		System.out.println(id);
		PrintWriter out = response.getWriter();
		int i=userdao.delete(id);
		if(i>0){
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Deleted Successfully');");
			out.println("location='user.jsp';");
			out.println("</script>");
		}  
		else{ 
			 out.println("<script type=\"text/javascript\">");
			 out.println("alert('Oops couldn't delete try again!!');");
			 out.println("location='user.jsp';");
			 out.println("</script>");
		}
	}
	
	protected void accountadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  Account ac = new Account();
	  PrintWriter out = response.getWriter();
	  ac.setAccountNumber(request.getParameter("acno"));
	  ac.setAccountName(request.getParameter("acname"));
	  ac.setCurrency(request.getParameter("curr"));
	  ac.setBranch(request.getParameter("branch"));
	  ac.setBalance(request.getParameter("bal"));
	  ac.setOpeningBalance(request.getParameter("obal"));
	  ac.setClosingBalance(request.getParameter("cbal"));
	  ac.setCorporateId(Integer.parseInt(request.getParameter("cid")));
	  ac.setCorporateName(request.getParameter("cname"));
	  int i=acdao.save(ac);
		if(i>0){
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Added Successfully');");
			out.println("location='account.jsp';");
			out.println("</script>");
		}  
		else{ 
			 out.println("<script type=\"text/javascript\">");
			 out.println("alert('Oops account not added try again!!');");
			 out.println("location='account.jsp';");
			 out.println("</script>");
		}
	}
	protected void accountdelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id") ;
		PrintWriter out = response.getWriter();
		int i=acdao.delete(id);
		int cid = acdao.getCorpId(id);
		if(i>0){
			StringBuffer sb = new StringBuffer("");
			sb.append("Accountslist.jsp");
			sb.append("?cid=" + cid);
			String url = sb.toString();
			String urlEncoded = response.encodeRedirectURL(url) ;
			response.sendRedirect(urlEncoded);
			
		}  
		else{ 
			 out.println("<script type=\"text/javascript\">");
			 out.println("alert('Oops couldn't delete try again!!');");
			 out.println("location='Accountslist.jsp';");
			 out.println("</script>");
		}
	}
}
