package com.pack.Bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pack.Bank.dao.AccountDao;
import com.pack.Bank.dao.BackOfficeUserDao;
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
	BackOfficeUserDao buserdao = new BackOfficeUserDao();
	
	public String  email,successMsg,userType;
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
	      session.setAttribute("successMsg","empty");
		  String password = request.getParameter("password");
		  if(email == null || "".equals(email) || password == null || "".equals(password)){
				throw new ServletException("Mandatory Parameter missing");
			}
		  System.out.println(email+" "+password);
		  String pass = buserdao.getUserByEmail(email);
		  System.out.println(pass);
		  if(pass!=null) {
			  if(pass.equals(password)) {
			  response.sendRedirect("backoffice.jsp");
			  String name=buserdao.getUsername(email);
			  System.out.println(name);
			  session.setAttribute("name", name);
			  session.setAttribute("userType", "back");
			  }
			  else if(!pass.equals(password)) {
				  System.out.println("Wrong password");
				  throw new IOException("Wrong Password");
			  }
		  }
		  else{
			   pass = udao.getUserByEmail(email);
			   if(pass==null) {
				  response.sendRedirect("loginError.jsp");
				  System.out.println("No such user");
			   }
			   else if(pass.equals(password)) {
				   int status = udao.getChangedPasswordStatus(email);
				   String name=udao.getUsername(email);
				   session.setAttribute("name", name);
				   session.setAttribute("userType", "front");
				   if(status==0)
					    response.sendRedirect("FrontOffice/frontoffice.jsp");
				   else
					    response.sendRedirect("FrontOffice/userhome.jsp");
			     }
			  else {
				   System.out.println("Wrong password");
				   throw new IOException("Wrong Password");
			  }
		  }  
	}
	protected void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
		  HttpSession session = request.getSession();
		  String password1 = request.getParameter("password1");
		  String password2 = request.getParameter("password2");
		  System.out.println(password1+" "+password2);
		  if(password1 == null || "".equals(password1) || password2 == null || "".equals(password2)){   
			     session.setAttribute("errorMsg","Mandatory parameter missing"); 
			     System.out.println("Mandatory parameter missing");
			     response.sendRedirect("FrontOffice/passError.jsp");
			}
		  else {
			  Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$");
			  Matcher m1 = p.matcher(password1);
			  Matcher m2 = p.matcher(password2);
		      if (!m1.matches() || !m2.matches()) {
		    	  session.setAttribute("errorMsg","Password must contain a uppercase, a lowercase, a digit and atleast 8 characters"); 
				  System.out.println("Password pattern is wrong");
			      response.sendRedirect("FrontOffice/passError.jsp");
		      }
		      else {
		    	  if(password1.equals(password2)) {
		    		 int status = udao.changePassword(email,password1);
		    		 if(status>0) {
		    			 String name=udao.getUsername(email);
		    			 session.setAttribute("name", name);
		    			 response.sendRedirect("FrontOffice/userhome.jsp");
		    		 }
		    		 else {
		    			 session.setAttribute("errorMsg","Oops we entered an error.");
		    			 response.sendRedirect("FrontOffice/passError.jsp");
		    		 }
		    	  }
		    	  else {
		    		  session.setAttribute("errorMsg","Both passwords should be same.");
					   System.out.println("Both passwords should be same");
					   response.sendRedirect("FrontOffice/passError.jsp");
				  }
			  }
		  }
	}
	protected void transadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  Transaction trans = new Transaction();
		  Account ac = new Account();
		  HttpSession session = request.getSession();
		  PrintWriter out = response.getWriter();
		  String acNo = request.getParameter("acNo");
		  String transType = request.getParameter("transType");
		  String transDate = request.getParameter("transDate");
		  String balance = request.getParameter("balance");
		  if(transDate == null || "".equals(transDate) || balance == null || "".equals(balance)){   
			     session.setAttribute("errorMsg","Mandatory parameter missing"); 
			     System.out.println("Mandatory parameter missing");
			     response.sendRedirect("FrontOffice/transError.jsp");
			}
		  int amt = Integer.parseInt(acdao.getBalanceFromAcNo(acNo));
		  System.out.println(" Amt = "+amt+" Balance= "+Integer.parseInt(balance));
		  if(Integer.parseInt(balance)>amt && transType.equals("credit")) {
			  session.setAttribute("errorMsg","Amount cannot be greater than balance."); 
			  System.out.println("Amount cannot be greater than balance.");
			  response.sendRedirect("FrontOffice/transError.jsp");
		  }
		  else {
			  trans.setAccountNumber(acNo);
			  trans.setTransactionType(transType);
			  trans.setTransactionDate(transDate);
			  trans.setBalance(balance);
			  if(transType.equals("credit")) 
				  amt = Integer.parseInt(balance) - amt;
			  else
				  amt = Integer.parseInt(balance) + amt;
			  System.out.println(amt);
		      int s_code = acdao.setBalanceAfterTrans(Integer.toString(amt),acNo);
			  int i=transdao.save(trans);
				if(i>0 && s_code>0){
					 session.setAttribute("successMsg","Transaction Added Successfully."); 
				     System.out.println("Transaction Added Successfully");
				     response.sendRedirect("FrontOffice/userhome.jsp");
				}  
				else{ 
					 out.println("<script type=\"text/javascript\">");
					 out.println("location='FrontOffice/transError.jsp';");
					 out.println("</script>");
				}
		  }  
	   }
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
	    response.sendRedirect("login.jsp");
	    System.out.println("logged out");
	}
	protected void corpadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session = request.getSession();  
		  Corporates corp = new Corporates();
		  PrintWriter out = response.getWriter();
		  String name = request.getParameter("corpName");
		  String address = request.getParameter("corpAddress");
		  String ph = request.getParameter("corpPh");
		  if(name == null || "".equals(name) || address == null || "".equals(address) || ph == null || "".equals(ph)){   
			     session.setAttribute("errorMsg","Mandatory parameter missing"); 
			     System.out.println("Mandatory parameter missing");
			     response.sendRedirect("CorporateSetUp/corporateError.jsp");
			}
		  else if(ph.length()!=10 || (ph.charAt(0)!='7' && ph.charAt(0)!='8' && ph.charAt(0)!='9')) {
			  session.setAttribute("errorMsg","Phone Number is invalid"); 
			  System.out.println("Phone Number is invalid");
			  response.sendRedirect("CorporateSetUp/corporateError.jsp");
		  }
		  else if(address.length()<5) {
			  session.setAttribute("errorMsg","Address is invalid"); 
			  System.out.println("Address is invalid");
			  response.sendRedirect("CorporateSetUp/corporateError.jsp");
		  }
		  else {
			  corp.setCorporateName(name);
			  corp.setAddress(address);
			  corp.setContactNumber(ph);
			  int i=corpdao.save(corp);
				if(i>0){
					session.setAttribute("successMsg","Corporate Added Successfully."); 
				     System.out.println("Corporate Added Successfully");
				     response.sendRedirect("CorporateSetUp/corporate.jsp");
				}  
				else{ 
					 out.println("<script type=\"text/javascript\">");
					 out.println("location='CorporateSetUp/corporateError.jsp';");
					 out.println("</script>");
				}
		  }
	}
	protected void corpupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  Corporates corp=new Corporates();
		  HttpSession session = request.getSession(); 
		  PrintWriter out = response.getWriter();
		  System.out.println("in update of servlet");
		  String address = request.getParameter("corpAddress");
		  String ph = request.getParameter("corpPh");
		  if(address == null || "".equals(address) || ph == null || "".equals(ph)){   
			     session.setAttribute("errorMsg","Mandatory parameter missing"); 
			     System.out.println("Mandatory parameter missing");
			     response.sendRedirect("CorporateSetUp/corporateUpdateError.jsp");
			}
		  else if(ph.length()!=10 || (ph.charAt(0)!='7' && ph.charAt(0)!='8' && ph.charAt(0)!='9')) {
			  session.setAttribute("errorMsg","Phone Number is invalid"); 
			  System.out.println("Phone Number is invalid");
			  response.sendRedirect("CorporateSetUp/corporateUpdateError.jsp");
		  }
		  else if(address.length()<5) {
			  session.setAttribute("errorMsg","Address is invalid"); 
			  System.out.println("Address is invalid");
			  response.sendRedirect("CorporateSetUp/corporateUpdateError.jsp");
		  }
		  else {
			  corp.setCorporateId(Integer.parseInt(request.getParameter("corpId")));
			  corp.setCorporateName(request.getParameter("corpName"));
			  corp.setAddress(address);
			  corp.setContactNumber(ph);
			  System.out.println("in servlet "+corp.getCorporateId()+" "+corp.getCorporateName()+" "+corp.getAddress());
			  int i=corpdao.update(corp); 
			  if(i>0){
				  session.setAttribute("successMsg","Corporate Updated Successfully."); 
				     System.out.println("Corporate Updated Successfully");
				     response.sendRedirect("CorporateSetUp/corporate.jsp");
				}  
				else{ 
					 out.println("<script type=\"text/javascript\">");
					 out.println("location='CorporateSetUp/corporateUpdateError.jsp';");
					 out.println("</script>");
				}
		  }
	}	
	protected void corpdelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		System.out.println("I am here in delete");
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		PrintWriter out = response.getWriter();
		int i=corpdao.delete(id);
		if(i>0){
			session.setAttribute("successMsg","Corporate Deleted Successfully."); 
		     System.out.println("Corporate Deleted Successfully");
		     response.sendRedirect("CorporateSetUp/corporate.jsp");
		}  
		else{ 
			 out.println("<script type=\"text/javascript\">");
			 out.println("location='CorporateSetUp/corporateError.jsp';");
			 out.println("</script>");
		}
	}
	protected void useradd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  User user = new User();
		  HttpSession session = request.getSession(); 
		  PrintWriter out = response.getWriter();
		  String loginId = request.getParameter("loginId");
		  String username = request.getParameter("username");
		  String address = request.getParameter("corpAddress");
		  String ph = request.getParameter("corpPh");
		  if(loginId == null || "".equals(loginId) || username == null || "".equals(username) || address == null || "".equals(address) || ph == null || "".equals(ph)){   
			     session.setAttribute("errorMsg","Mandatory parameter missing"); 
			     System.out.println("Mandatory parameter missing");
			     response.sendRedirect("UserSetUp/userError.jsp");
			}
		  else if(ph.length()!=10 || (ph.charAt(0)!='7' && ph.charAt(0)!='8' && ph.charAt(0)!='9')) {
			  session.setAttribute("errorMsg","Phone Number is invalid"); 
			  System.out.println("Phone Number is invalid");
			  response.sendRedirect("UserSetUp/userError.jsp");
		  }
		  else if(address.length()<5) {
			  session.setAttribute("errorMsg","Address is invalid"); 
			  System.out.println("Address is invalid");
			  response.sendRedirect("UserSetUp/userError.jsp");
		  }
		  else {
			  user.setCorporateId(Integer.parseInt(request.getParameter("corpId")));
			  user.setLoginId(loginId);
			  user.setPassword("userC123#");
			  user.setUserName(username);
			  user.setDepartment(request.getParameter("department"));
			  user.setAddress(address);
			  user.setContactNumber(ph);
			  int i=udao.save(user);
				if(i>0){
					session.setAttribute("successMsg","User Added Successfully."); 
				     System.out.println("User Added Successfully");
				     response.sendRedirect("UserSetUp/user.jsp");
				}  
				else{ 
					 out.println("<script type=\"text/javascript\">");
					 out.println("location='UserSetUp/userError.jsp';");
					 out.println("</script>");
				}
		  }
	}
	protected void userupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  System.out.println("in update of servlet");  
		  User user = new User();
		  HttpSession session = request.getSession(); 
		  PrintWriter out = response.getWriter();
		  String id = request.getParameter("loginId");
		  String address = request.getParameter("corpAddress");
		  String ph = request.getParameter("corpPh");
		  if(address == null || "".equals(address) || ph == null || "".equals(ph)){   
			     session.setAttribute("errorMsg","Mandatory parameter missing"); 
			     System.out.println("Mandatory parameter missing");
			     response.sendRedirect("UserSetUp/userUpdateError.jsp");
			}
		  else if(ph.length()!=10 || (ph.charAt(0)!='7' && ph.charAt(0)!='8' && ph.charAt(0)!='9')) {
			  session.setAttribute("errorMsg","Phone Number is invalid"); 
			  session.setAttribute("id", id);
			  System.out.println("Phone Number is invalid");
			  response.sendRedirect("UserSetUp/userUpdateError.jsp");
		  }
		  else if(address.length()<5) {
			  session.setAttribute("errorMsg","Address is invalid"); 
			  System.out.println("Address is invalid");
			  response.sendRedirect("UserSetUp/userUpdateError.jsp");
		  }
		  else {
			  user.setCorporateId(Integer.parseInt(request.getParameter("corpId")));
			  user.setLoginId(id);
			  user.setUserName(request.getParameter("username"));
			  user.setDepartment(request.getParameter("department"));
			  user.setAddress(address);
			  user.setContactNumber(ph);
			  
			  System.out.println("in servlet "+user.getCorporateId()+" "+user.getUserName()+" "+user.getAddress());
			  int i=udao.update(user); 
			  if(i>0){
				  session.setAttribute("successMsg","User Updated Successfully."); 
				     System.out.println("User Updated Successfully");
				     response.sendRedirect("UserSetUp/user.jsp");
				}  
				else{ 
					 out.println("<script type=\"text/javascript\">");
					 out.println("location='UserSetUp/userUpdateError.jsp';");
					 out.println("</script>");
				}
		  }
	}	
	protected void userdelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		String id=request.getParameter("id") ;
		System.out.println(id);
		PrintWriter out = response.getWriter();
		int i=udao.delete(id);
		if(i>0){
			session.setAttribute("successMsg","User Deleted Successfully."); 
		     System.out.println("User Deleted Successfully");
		     response.sendRedirect("UserSetUp/user.jsp");
		}  
		else{ 
			 out.println("<script type=\"text/javascript\">");
			 out.println("location='UserSetUp/userError.jsp';");
			 out.println("</script>");
		}
	}
	
	protected void accountadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  Account ac = new Account();
	  PrintWriter out = response.getWriter();
	  HttpSession session = request.getSession(); 
	  String acNo = request.getParameter("acno");
	  String acName = request.getParameter("acname");
	  String bal = request.getParameter("bal");
	  String obal = request.getParameter("obal");
	  String cbal = request.getParameter("cbal");
	  int cid = Integer.parseInt(request.getParameter("cid"));
	  Boolean res = acdao.checkACName(acName);
	  Boolean res1 = acdao.checkACNo(acNo);
	  System.out.println(res+" "+res1);
	  if(acNo == null || "".equals(acNo) || acName == null || "".equals(acName) || bal == null || "".equals(bal)){   
		     session.setAttribute("errorMsg","Mandatory parameter missing"); 
		     System.out.println("Mandatory parameter missing");
		     response.sendRedirect("AccountSetUp/accountError.jsp");
		}
	  else if(acNo.length()!=10) {
		     session.setAttribute("errorMsg","Invalid A/C No"); 
		     System.out.println("Invalid A/C No");
		     response.sendRedirect("AccountSetUp/accountError.jsp");
	  }
	  else if(res1) {
		     session.setAttribute("errorMsg","A/C no already exists"); 
		     System.out.println("Invalid A/C No");
		     response.sendRedirect("AccountSetUp/accountError.jsp");
	  }
	  else if(res) {
		     session.setAttribute("errorMsg","A/C name already exists"); 
		     System.out.println("Invalid A/C Name");
		     response.sendRedirect("AccountSetUp/accountError.jsp");
	  }
	  else if(Integer.parseInt(bal)<Integer.parseInt(obal) || Integer.parseInt(bal)>Integer.parseInt(cbal)) {
		     session.setAttribute("errorMsg","Balance should be more than opening balance and less than closing balance"); 
		     System.out.println("Invalid Balance");
		     response.sendRedirect("AccountSetUp/accountError.jsp");
	  }
	  else {
		  ac.setAccountNumber(acNo);
		  ac.setAccountName(acName);
		  ac.setCurrency(request.getParameter("curr"));
		  ac.setBranch(request.getParameter("branch"));
		  ac.setBalance(bal);
		  ac.setOpeningBalance(obal);
		  ac.setClosingBalance(cbal);
		  ac.setCorporateId(cid);
		  ac.setCorporateName(request.getParameter("cname"));
		  int i=acdao.save(ac);
			if(i>0){
				session.setAttribute("successMsg","Account Added Successfully."); 
			     System.out.println("Account added Successfully");
			     StringBuffer sb = new StringBuffer("");
					session.setAttribute("successMsg","Account Closed Successfully."); 
				     System.out.println("Account deleted Successfully");
					sb.append("AccountSetUp/Accountslist.jsp");
					sb.append("?cid=" + cid);
					String url = sb.toString();
					String urlEncoded = response.encodeRedirectURL(url) ;
					response.sendRedirect(urlEncoded);
			}  
			else{ 
				 out.println("<script type=\"text/javascript\">");
				 out.println("location='AccountSetUp/accountError.jsp';");
				 out.println("</script>");
			}
	    }
	}
	protected void accountdelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id=request.getParameter("id") ;
		PrintWriter out = response.getWriter();
		int i=acdao.delete(id);
		int cid = acdao.getCorpId(id);
		if(i>0){
			StringBuffer sb = new StringBuffer("");
			session.setAttribute("successMsg","Account Closed Successfully."); 
		     System.out.println("Account deleted Successfully");
			sb.append("AccountSetUp/Accountslist.jsp");
			sb.append("?cid=" + cid);
			String url = sb.toString();
			String urlEncoded = response.encodeRedirectURL(url) ;
			response.sendRedirect(urlEncoded);
			
		}  
		else{ 
			 out.println("<script type=\"text/javascript\">");
			 out.println("location='AccountSetUp/accountError.jsp';");
			 out.println("</script>");
		}
	}
}
