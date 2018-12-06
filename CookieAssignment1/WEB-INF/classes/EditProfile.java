package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class EditProfile extends HttpServlet
{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request,response);
		String username = request.getParameter("username");
		String newemail = request.getParameter("newemail");
		String newphone = request.getParameter("newphone");

		try
		{
			
			Connection con=DBConnection.getCon();

			PreparedStatement ps=con.prepareStatement("UPDATE PROFILE SET PHONE=? , EMAIL=? where username=?");
			
			ps.setString(1,newphone);
			ps.setString(2,newemail);
			ps.setString(3,username);
			

			int k=ps.executeUpdate();
				
			out.print("User profile Successfully updated");
			//RequestDispatcher rd=req.getRequestDispatcher("login.html");
			//rd.include(request,response);
		}
		catch (Exception e)
		{
			out.println(e);
		}
		
		out.close();
	}
}
