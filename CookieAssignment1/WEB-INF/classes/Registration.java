package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Registration extends HttpServlet
{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException
	{
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String username=request.getParameter("username");
		String password=request.getParameter("password");

		try
		{
			
			Connection con=DBConnection.getCon();

			PreparedStatement ps=con.prepareStatement("INSERT INTO USERLOGINCOOKIE VALUES(?,?)");
			ps.setString(1,username);
			ps.setString(2,password);

			int k= ps.executeUpdate();
			
			out.print("Registration Successful");
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request,response);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		out.close();

	}
	
	
}
