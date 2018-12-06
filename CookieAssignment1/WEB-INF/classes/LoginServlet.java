package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class LoginServlet extends HttpServlet 
{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request,response);
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		try
		{
			
			Connection con=DBConnection.getCon();

			PreparedStatement ps=con.prepareStatement("SELECT * FROM USERLOGINCOOKIE where username=? and password=?");
			ps.setString(1,name);
			ps.setString(2,password);
			

			ResultSet rs=ps.executeQuery();
				
			if(rs.next())
			{
				out.print("You have been sucessfully logged in");
				Cookie ck=new Cookie("name",name);
				response.addCookie(ck);
			}
			else
			{
				out.print("Sorry username or password doesnt match");
				request.getRequestDispatcher("login.html").include(request,response);
				out.print("<a href='registration.html'>Register</a>");
			}
		}
		catch (Exception e)
		{
			out.println(e);
		}
		
		out.close();
	}
}
