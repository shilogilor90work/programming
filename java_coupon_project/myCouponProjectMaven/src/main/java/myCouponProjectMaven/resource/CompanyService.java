package myCouponProjectMaven.resource;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

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

import exceptions.DuplicateCouponTypeException;
import exceptions.MyExceptions;
import exceptions.WrongDataInputException;
import facade.CompanyFacade;
import members.Coupon;
import utilities.CouponType;

@Path("CompanyService")
public class CompanyService {
	
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	Gson gson = new Gson();
	
	CompanyFacade tempCompany = null; 
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
	     return "Got it!";
	}
	 
	/**
	 * 
	 * @param c is the coupon that is sent to be created
	 * @return returns the response it can be a succses message or a failer message
	 */
	@POST
	@Path("/createCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response createCoupon(Coupon c)
	{
		tempCompany =  (CompanyFacade) request.getSession().getAttribute("tempFacade");
		try {
			tempCompany.createCoupon(c);
		} catch (ClassNotFoundException | SQLException | DuplicateCouponTypeException | InterruptedException
				| ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
		return javax.ws.rs.core.Response.ok("the coupon  " + c.getTitle() + "was successfuly created").status(200).build();

	}
	
	/**
	 * 
	 * @param c is the coupon that is sent to be removed
	 * @return returns the response it can be a succses message or a failer message
	 */
	@POST
	@Path("/removeCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response removeCoupon(Coupon c)
	{
		tempCompany =  (CompanyFacade) request.getSession().getAttribute("tempFacade");
		try {
			tempCompany.removeCoupon(c);
		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
		return javax.ws.rs.core.Response.ok("the coupon  " + c.getTitle() + "was successfuly deleted").status(200).build();

	}
	
	/**
	 * 
	 * @param c is the coupon that is sent to be updated
	 * @return returns the response it can be a succses message or a failer message
	 */
	@POST
	@Path("/updateCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response updateCoupon(Coupon c)
	{
		tempCompany =  (CompanyFacade) request.getSession().getAttribute("tempFacade");
		try {
			tempCompany.updateCoupon(c);
		} catch (ClassNotFoundException | SQLException | WrongDataInputException | InterruptedException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
		return javax.ws.rs.core.Response.ok("the coupon  " + c.getTitle() + "was successfuly updated").status(200).build();

		
	}
	
	/**
	 * 
	 * @param id is the id of the coupon requested
	 * @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCoupon(long id)
	{
		tempCompany =  (CompanyFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempCompany.getCoupon(id))).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}

	}
	
	
	/**
	 * 
	 *  @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getAllCoupons")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCoupons()
	{
		tempCompany =  (CompanyFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempCompany.getAllCoupons())).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
	}
	
	/**
	 * 
	 * @param couponType is the coupon type requested
	 * @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getCouponsByType")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCouponsByType(CouponType couponType)
	{
		tempCompany =  (CompanyFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempCompany.getCouponsByType(couponType))).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
	}
	
	/**
	 * 
	 * @param price is the price requested
	 * @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getCouponsByPrice")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCouponsByPrice(double price)
	{
		tempCompany =  (CompanyFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempCompany.getCouponsByPrice(price))).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
	}
	
	/**
	 * 
	 * @param date is the date requested
	 * @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getCouponsByDate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCouponsByDate(Date date)
	{
		tempCompany =  (CompanyFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempCompany.getCouponsByDate(date))).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
	}
	
	/**
	 * 
	 * @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/printCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response printCompany()
	{
		tempCompany =  (CompanyFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempCompany.printCompany())).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
	}
	
	
}
