package myCouponProjectMaven.resource;

import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import exceptions.DuplicateUserException;
import exceptions.MyExceptions;
import exceptions.NullPointerException;
import exceptions.WrongDataInputException;
import facade.AdminFacade;
import members.Company;
import members.Customer;


@Path("AdminService")
public class AdminService {
	
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	Gson gson = new Gson();

	AdminFacade tempAdmin = null;
/**
 *
 * @param c is the company that is sent to be created
 * @return returns the response it can be a succses message or a failer message
 */
	@POST
	@Path("/createCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response createCompany(Company c)
	{
		
		tempAdmin = (AdminFacade) request.getSession().getAttribute("tempFacade");
		try {
			tempAdmin.createCompany(c);
		} catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateUserException e) {
			e.printStackTrace();
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
		return javax.ws.rs.core.Response.ok(c.getCompName() + "was successfuly created").status(200).build();
	}
	/**
	 * 
	 * @param c  is the company that is sent to be removed
	 * @return  returns the response it can be a succses message or a failer message
	 */
	@POST
	@Path("/removeCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response removeCompany(Company c)
	{
		tempAdmin = (AdminFacade) request.getSession().getAttribute("tempFacade");
		try {
			tempAdmin.removeCompany(c);
		} catch (ClassNotFoundException | SQLException | InterruptedException | NullPointerException
				| ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
			}
			return javax.ws.rs.core.Response.ok(c.getCompName() + "was successfuly deleted").status(200).build();
		
	}
	/**
	 * 
	 * @param c is the company that is sent to be updated
	 * @return returns the response it can be a succses message or a failer message
	 */
	@POST
	@Path("/updateCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response updateCompany(Company c)
	{
		tempAdmin = (AdminFacade) request.getSession().getAttribute("tempFacade");
		try {
			tempAdmin.updateCompany(c);
		} catch (ClassNotFoundException | SQLException | WrongDataInputException | InterruptedException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
		return javax.ws.rs.core.Response.ok(c.getCompName() + "was successfuly updated").status(200).build();
	
	}
	
	/**
	 * 
	 * @param c is the company that is sent to be called by id
	 * @return  returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getCompanyById")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCompanyById(Company c)
	{
		tempAdmin = (AdminFacade) request.getSession().getAttribute("tempFacade");
		 try {
				return javax.ws.rs.core.Response.ok(gson.toJson(tempAdmin.getCompany(c.getId()))).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
	
	}

	/**
	 * 
	 * @return  returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getAllCompanies")
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCompanies() 
	{
		tempAdmin = (AdminFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempAdmin.getAllCompanies())).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
			}
	}
	
	/**
	 * 
	 * @param c is the customer that is sent to be created
	 * @return  returns the response it can be a succses message or a failer message
	 */
	@POST
	@Path("/createCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response createCustomer(Customer c)
	{
		tempAdmin = (AdminFacade) request.getSession().getAttribute("tempFacade");
		try {
			tempAdmin.createCustomer(c);
		} catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateUserException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
		return javax.ws.rs.core.Response.ok(c.getCustName() + "was successfuly created").status(200).build();
		
	}

	/**
	 * 
	 * @param c is the customer that is sent to be removed
	 * @return  returns the response it can be a succses message or a failer message
	 */
	@POST
	@Path("/removeCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response removeCustomer(Customer c)
	{
		tempAdmin = (AdminFacade) request.getSession().getAttribute("tempFacade");
		try {
			tempAdmin.removeCustomer(c);
		} catch (ClassNotFoundException | SQLException | InterruptedException | WrongDataInputException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
		return javax.ws.rs.core.Response.ok(c.getCustName() + "was successfuly deleted").status(200).build();
	}
	
	/**
	 * 
	 * @param c is the customer that is sent to be updated
	 * @return returns the response it can be a succses message or a failer message
	 */
	@POST
	@Path("/updateCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response updateCustomer(Customer c)
	{
		tempAdmin = (AdminFacade) request.getSession().getAttribute("tempFacade");
		try {
			tempAdmin.updateCustomer(c);
		} catch (ClassNotFoundException | WrongDataInputException | SQLException | InterruptedException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
		return javax.ws.rs.core.Response.ok(c.getCustName() + "was successfuly updated").status(200).build();

	}
	
	
	/**
	 * 
	 * @param c is the customer that is sent to be called by id
	 * @return  returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getCustomerById")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCustomerById(Customer c)
	{
		tempAdmin = (AdminFacade) request.getSession().getAttribute("tempFacade");
		 try {			
			 return javax.ws.rs.core.Response.ok(gson.toJson(tempAdmin.getCustomer(c.getId()))).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
	}

	/**
	 * 
	 * @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getAllCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCustomers() 
	{
		tempAdmin = (AdminFacade) request.getSession().getAttribute("tempFacade");
		try {
			 return javax.ws.rs.core.Response.ok(gson.toJson(tempAdmin.getAllCustomers())).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
	}
}
