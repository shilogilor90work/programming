package myCouponProjectMaven.myCouponProjectMaven;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backgroundFunctions.CouponSystem;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;

/**
 * Servlet implementation class Entry
 */
@WebServlet("/LoginNavigator")
public class LoginNavigator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginNavigator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String clientType = request.getParameter("usertype");
		PrintWriter out = response.getWriter();

		switch (clientType)
		{
			case "admin":
			{
				AdminFacade tempAdmin = null;
				try {
					tempAdmin = (AdminFacade) CouponSystem.getInstance().login(userName, password, clientType);
				} catch (ClassNotFoundException | SQLException | InterruptedException e) {
					out.println(e);

				}

				if (tempAdmin!=null)
				{
					request.getSession().setAttribute("tempFacade", tempAdmin);
					response.sendRedirect("http://localhost:8080/myCouponProjectMaven/adminSPA/admin.html");
				}
				else
				{
					response.sendRedirect("http://localhost:8080/myCouponProjectMaven/failLogin.html");
				}
				break;
			}
			case "company":
			{
				CompanyFacade tempCompany = null;
				try {
					tempCompany = (CompanyFacade) CouponSystem.getInstance().login(userName, password, clientType);
				} catch (ClassNotFoundException | SQLException | InterruptedException e) {
					out.println(e);

				}

				if (tempCompany!=null)
				{
					request.getSession().setAttribute("tempFacade", tempCompany);
					response.sendRedirect("http://localhost:8080/myCouponProjectMaven/companySPA/company.html");
				}
				else
				{
					response.sendRedirect("http://localhost:8080/myCouponProjectMaven/failLogin.html");
				}
				break;
			}
			case "customer":
			{
				
				CustomerFacade tempCustomer = null;
				try {
					tempCustomer = (CustomerFacade) CouponSystem.getInstance().login(userName, password, clientType);
				} catch (ClassNotFoundException | SQLException | InterruptedException e) {
					out.println(e);

				}
				if (tempCustomer!=null)
				{
					request.getSession().setAttribute("tempFacade", tempCustomer);
					response.sendRedirect("http://localhost:8080/myCouponProjectMaven/customerSPA/customer.html");
				}
				else
				{
					response.sendRedirect("http://localhost:8080/myCouponProjectMaven/failLogin.html");
				}
				break;
			}		
		}
	
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
