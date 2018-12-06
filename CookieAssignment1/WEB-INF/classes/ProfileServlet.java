package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class ProfileServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request,response);
		
		Cookie ck[]=request.getCookies();

	try
	{
		if(ck!=null)
		{
			String name=ck[0].getValue();
			
					Connection con=DBConnection.getCon();

					PreparedStatement ps=con.prepareStatement("SELECT * FROM PROFILE where username=?");
					ps.setString(1,name);
					
					ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					out.print("<b>Welcome to Profile</b>");
					out.print("<br>Welcome , "+name);
					out.print("<br>phone no: "+rs.getString(2));
					out.print("<br>email: "+rs.getString(3));

					
				}
				else
				{
					//request.getRequestDispatcher("ProfileServlet").include(request,response);
					out.println("<a href='createprofile.html'>click to create profile</a>");
				}
			
			
			
		}
			else
			{
				out.print("Please Login First");
				request.getRequestDispatcher("login.html").include(request,response);
			}
		}
		catch (Exception e)
				{
					out.println(e);
				}	
		out.close();
		
	}
}
