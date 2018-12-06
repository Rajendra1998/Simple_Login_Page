package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class CreateProfile extends HttpServlet
{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException
	{
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		request.getRequestDispatcher("link.html").include(request,response);
		String username=request.getParameter("username");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		out.println("<h1>hello</h1>");
		

		try
		{
			Connection con=DBConnection.getCon();

			PreparedStatement ps=con.prepareStatement("INSERT INTO PROFILE VALUES(?,?,?)");
			ps.setString(1,username);
			ps.setString(2,phone);
			ps.setString(3,email);
			int k=ps.executeUpdate();
			
			out.print(k+" Profile Created");	
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		out.close();
	}
}
