

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutPage
 */
public class LogoutPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
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
		PrintWriter out = response.getWriter();
		out.println("<center><h3>You are sucessfully logged out!!!</h3></center>");
		request.getSession().invalidate();
		
		//OR response.getWriter().println("Logged out");
		
//		HttpSession session=request.getSession(false);
//		if(session!=null)
//		{
//			session.invalidate();
//		
//		}
////		
		RequestDispatcher rd = request.getRequestDispatcher("/BankLoginPage.html");
		rd.include(request, response);
		//response.sendRedirect(request.getContextPath() + "/BankLoginPage.html");
//		System.out.println("Logged out");
		
		

	}

}
