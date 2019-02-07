package facade;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import dbdao.CustomerDBDAO;
import exceptions.CustomerHasCouponAlready;
import exceptions.MyExceptions;
import exceptions.UnavailableCouponException;
import members.Coupon;
import facade.CouponClientFacade;
import dbdao.CouponDBDAO;
import utilities.CouponType;
/**
 * The class CustomerFacade is part of myCouponProject
 * Facade is a design pattern that is being used in myCouponProject
 * @author oshra & shilo
 *
 */

public class CustomerFacade implements CouponClientFacade, Serializable{

	long loginId;
	private CustomerDBDAO daocustomer= new CustomerDBDAO();
	private CouponDBDAO daocoupon = new CouponDBDAO();

	/**
	 * method purchaseCoupon purchases a coupon
	 * @param Coupon coupon stores the coupon that is being purchased
	 * @throws ParseException 
	 * @throws UnavailableCouponException 
	 * @throws CustomerHasCouponAlready 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see purchaseCoupon in CustomerDBDAO
	 */
	public void purchaseCoupon (Coupon coupon) throws ClassNotFoundException, SQLException, InterruptedException, CustomerHasCouponAlready, UnavailableCouponException, ParseException 
	{
		
				daocustomer.purchaseCoupon(coupon);
			
		
	}
	
	
	
	
	public Collection<Coupon> getAllCoupons() throws ClassNotFoundException, SQLException, InterruptedException, ParseException 
	{
		Set<Coupon> coupons =new HashSet<>(); 
	
			coupons=(Set<Coupon>) daocustomer.getAllCoupons();
		
		return coupons;
	}
	
	
	public Collection<Coupon> getAllCouponsByType(String type) throws ClassNotFoundException, SQLException, InterruptedException, ParseException 
	{
		Set<Coupon> coupons =new HashSet<>(); 
	
			coupons=(Set<Coupon>) daocustomer.getAllCouponsByType(type);
		
		return coupons;
	}
	
	/**
	 * method getAllPurchasedCoupons returns all purchased coupons
	 * @return Collection<Coupon> returns all purchased coupons 
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see getCoupons in CustomerDBDAO 
	 */
	public Collection<Coupon> getAllPurchasedCoupons() throws ClassNotFoundException, SQLException, InterruptedException, ParseException 
	{
		Set<Coupon> coupons =new HashSet<>(); 
	
			coupons=(Set<Coupon>) daocustomer.getCoupons();
		
		return coupons;
	}
	/**
	 *  method getAllPurchasedCouponsByType returns all purchased coupons of a certain type
	 * @param CouponType type stores the type of coupons that need to be returned
	 * @return Collection<Coupon> returns all purchased coupons of the required type
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see getCouponsByType in CouponDBDAO
	 */
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType type) throws ClassNotFoundException, SQLException, InterruptedException, ParseException 
	{
		Coupon temp= null;
		Set<Coupon> couponsFinal =new HashSet<>(); 
		Set<Coupon> coupons =new HashSet<>(); 
			coupons=(Set<Coupon>) daocoupon.getCouponByType(type.toString());
		
		Iterator<Coupon> iter = coupons.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
				if (daocustomer.checkCustomerToCoupon(loginId, temp.getId()))
				{
					couponsFinal.add(temp);
				}
		}
		return couponsFinal;
	}
	/**
	 *  method getAllPurchasedCouponsByPrice returns all purchased coupons bellow a certain price
	 * @param double price stores the maximum price of the coupons that need to be returned
	 * @return Collection<Coupon> returns all purchased coupons that are below the maximum price
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price) throws ClassNotFoundException, SQLException, InterruptedException, ParseException{
		Coupon temp= null;
		Set<Coupon> couponsFinal =new HashSet<>(); 
		Set<Coupon> coupons =new HashSet<>();
			coupons=(Set<Coupon>) daocustomer.getCoupons();
		
		Iterator<Coupon> iter = coupons.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if (temp.getPrice()<=price)
			{
				couponsFinal.add(temp);
			}
		}
		return couponsFinal;
	}	
	/**
	 * this method logs in 
	 * @return if successful it returns itself otherwise returns null
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public CouponClientFacade login(String name, String password, String clientType) throws ClassNotFoundException, SQLException, InterruptedException {
			if (daocustomer.login(name, password))
			{
				return this;
			}
		
		return null;
	}

}
