

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpPage
 */
public class SignUpPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    Connection conn = null;
	public void init(ServletConfig config) throws ServletException 
	{
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//response.getWriter().println("Sign Up completed successfully");
		String name1=request.getParameter("name");
		String username1=request.getParameter("uname");
		String password1=request.getParameter("password");
		int balance1=Integer.parseInt(request.getParameter("balance"));
		
		try
		{
			
			PreparedStatement pst = conn.prepareStatement("INSERT INTO register VALUES(?,?,?,?)");
			pst.setString(1,name1);
			pst.setString(2,username1);
			pst.setString(3,password1);
			pst.setInt(4, balance1);
			
			int n =pst.executeUpdate();
			//this returns int value
			if(n!=0)
			{
				PrintWriter out = response.getWriter();
				out.println("<center><h1>Sucessfully registered...use your credentials to login!!</h1></center>");
				out.println("<br>");
				RequestDispatcher rd=request.getRequestDispatcher("/BankLoginPage.html");
				rd.include(request, response);
			}
			else
			{
				System.out.println("Record is not inserted");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

}
