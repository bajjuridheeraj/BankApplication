

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginPageServlet
 */
public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public LoginPageServlet() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	Connection conn = null;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		ServletContext context = config.getServletContext();
		String d = context.getInitParameter("driver");
		String u=context.getInitParameter("url");
		String us=context.getInitParameter("username");
		String pwd=context.getInitParameter("password");
		
		
		try{
			Class.forName(d);
			conn = DriverManager.getConnection(u,us,pwd);

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/** 
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try{
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String uname=request.getParameter("username");
		String pass=request.getParameter("password");

		try {
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery("select * from login1 where name='"+uname+"' and pass='"+pass+"'");

			if(rs.next())
		{
				HttpSession session = request.getSession();
				if(session!=null)
					{
					  session.setAttribute("username", uname);
					session.setAttribute("password", pass);
					}
				
				//out.println("WELCOME TO OUR WEBSITE");
				RequestDispatcher rd=request.getRequestDispatcher("/MemberAccountPage.jsp");
				rd.forward(request, response);
			}
			else
			{
				out.println("Please check user name / password");
				RequestDispatcher rd=request.getRequestDispatcher("/BankLoginPage.html");
				rd.include(request,response);
			}
		}

		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		
	}
}

