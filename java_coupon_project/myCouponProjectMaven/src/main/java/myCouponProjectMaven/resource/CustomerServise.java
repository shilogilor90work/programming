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

import exceptions.CustomerHasCouponAlready;
import exceptions.MyExceptions;
import exceptions.UnavailableCouponException;
import facade.CustomerFacade;
import members.Coupon;
import utilities.CouponType;


@Path("CustomerService")
public class CustomerServise {

	@Context HttpServletRequest request;
	@Context HttpServletResponse response;

	CustomerFacade tempCustomer = null;
	Gson gson = new Gson();

	/**
	 * 
	 * @param coupon is the coupon wanyed to be purchased
	 * @return returns the response it can be a succses message or a failer message
	 */
	@POST
	@Path("/purchaseCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response purchaseCoupon(Coupon coupon)
	{
		tempCustomer = (CustomerFacade) request.getSession().getAttribute("tempFacade");
		try {
			tempCustomer.purchaseCoupon(coupon);
		} catch (ClassNotFoundException | SQLException | InterruptedException | CustomerHasCouponAlready
				| UnavailableCouponException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
			}
			return javax.ws.rs.core.Response.ok(coupon.getTitle() + "was successfuly purchased").status(200).build();
	}

	/**
	 * 
	 * @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getAllPurchasedCoupons")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllPurchasedCoupons()
	{
		tempCustomer = (CustomerFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempCustomer.getAllPurchasedCoupons())).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();

		}
	}
	
	/**
	 * 
	 * @param type is the type of coupon requested 
	 * @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getAllPurchasedCouponsByType")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllPurchasedCouponsByType(CouponType type)
	{
		tempCustomer = (CustomerFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempCustomer.getAllPurchasedCouponsByType(type))).status(200).build();

		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();

		}
	}
	
	/**
	 * 
	 * @param price is the price of coupon requested
	 * @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getAllPurchasedCouponsByPrice")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllPurchasedCouponsByPrice(double price)
	{
		tempCustomer = (CustomerFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempCustomer.getAllPurchasedCouponsByPrice(price))).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
	}
	
	/**
	 * 
	 * @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getAllAndEveryCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllAndEveryCoupon()
	{
		tempCustomer = (CustomerFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempCustomer.getAllCoupons())).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
	}
	
	/**
	 * 
	 * @param type is the type of coupon requested
	 * @return returns the response it can be a succses message or a failer message
	 */
	@GET
	@Path("/getAllAndEveryCouponByType")
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllAndEveryCouponByType(String type)
	{
		tempCustomer = (CustomerFacade) request.getSession().getAttribute("tempFacade");
		try {
			return javax.ws.rs.core.Response.ok(gson.toJson(tempCustomer.getAllCouponsByType(type))).status(200).build();
		} catch (ClassNotFoundException | SQLException | InterruptedException | ParseException e) {
			return javax.ws.rs.core.Response.ok(MyExceptions.Exceptions(e)).status(500).build();
		}
	}
	
	}
