package facade;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import exceptions.DuplicateCouponTypeException;
import exceptions.MyExceptions;
import exceptions.WrongDataInputException;
import members.Company;
import dbdao.CompanyDBDAO;
import members.Coupon;
import facade.CouponClientFacade;
import dbdao.CouponDBDAO;
import utilities.CouponType;

/**
 * The class CompanyFacade is part of myCouponProject
 * Facade is a design pattern that is being used in myCouponProject
 * @author oshra & shilo
 *
 */
public class CompanyFacade implements CouponClientFacade, Serializable{

	long loginId;
	private CompanyDBDAO daocompany= new CompanyDBDAO();
	private CouponDBDAO daocoupon = new CouponDBDAO();
	/**
	 * method createCoupon creates a new coupon by calling CouponDBDAO
	 * @see CouponDBDAO
	 * @param Coupon coupon stores the coupon that is being added to the company's coupons
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws DuplicateCouponTypeException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void createCoupon(Coupon coupon) throws ClassNotFoundException, SQLException, DuplicateCouponTypeException, InterruptedException, ParseException {
		
	
			daocompany.createCoupon(coupon);
		
		}

	/**
	 * method removeCoupon removes a coupon by calling CouponDBDAO
	 * @see CouponDBDAO
	 * @param Coupon coupon stores the coupon that is being removed from the company's coupons
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void removeCoupon(Coupon coupon) throws ClassNotFoundException, SQLException, InterruptedException, ParseException {
		
			daocoupon.removeCoupon(coupon);	
	
	}

	/**
	 * method updateCoupon updates an existing coupon by calling CouponDBDAO
	 * @see CouponDBDAO
	 * @param Coupon coupon stores the coupon that is being updated in the company's coupons
	 * @throws InterruptedException 
	 * @throws WrongDataInputException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	
	public void updateCoupon(Coupon coupon) throws ClassNotFoundException, SQLException, WrongDataInputException, InterruptedException {
		
			daocoupon.updateCoupon(coupon);
				
	}
	/**
	 * method getCoupon returns a coupon according to an ID it receives
	 * @param long id stores the id of the coupon that needs to be returned
	 * @return Coupon coupon returns the coupon with the matching ID
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	
	
	public Coupon getCoupon(long id) throws ClassNotFoundException, SQLException, InterruptedException, ParseException  {
			
		return daocoupon.getCoupon(id);
		
	}

	/**
	 * method getAllCoupons returns all of the company's coupons
	 * @return Collection<Coupon> returns all of the company's coupons
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Collection<Coupon> getAllCoupons() throws ClassNotFoundException, SQLException, InterruptedException, ParseException {
		
				return daocompany.getCoupons();
			
	}
	/**
	 * method getCouponByType returns all coupons of a specific type
	 * @param CouponType couponType stores the type of coupons that need to be returned
	 * @return Collection<Coupon> couponsFinal returns all the coupons with the matching type
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	
	public Collection<Coupon> getCouponsByType(CouponType couponType) throws ClassNotFoundException, SQLException, InterruptedException, ParseException {
		Coupon temp= null;
		Set<Coupon> couponsFinal =new HashSet<>(); 
		Set<Coupon> coupons =new HashSet<>();
			coupons=(Set<Coupon>) daocompany.getCoupons();
		Iterator<Coupon> iter = coupons.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if (temp.getType().equals(couponType))
			{
				couponsFinal.add(temp);
			}
		}
		return couponsFinal;
	}
	/**
	 * method getCouponByPrice returns all coupons that are below a certain price
	 * @param double price stores the maximum price of the coupons that need to be returned
	 * @return Collection<Coupon> couponsFinal returns all the coupons below the maximum price
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
		public Collection<Coupon> getCouponsByPrice(double price) throws ClassNotFoundException, SQLException, InterruptedException, ParseException {
		Coupon temp= null;
		Set<Coupon> couponsFinal =new HashSet<>(); 
		Set<Coupon> coupons =new HashSet<>();
			coupons=(Set<Coupon>) daocompany.getCoupons();
		
		Iterator<Coupon> iter = coupons.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if (temp.getPrice()<price)
			{
				couponsFinal.add(temp);
			}
		}
		return couponsFinal;
	} 
		/**
		 * method getCouponByDate returns all coupons that start before a certain date
		 * @param Date date stores the date that we are comparing all coupons to
		 * @return Collection<Coupon> couponsFinal returns all the coupons that start before the given date
		 * @throws ParseException 
		 * @throws InterruptedException 
		 * @throws SQLException 
		 * @throws ClassNotFoundException 
		 */
	public Collection<Coupon> getCouponsByDate(Date date) throws ClassNotFoundException, SQLException, InterruptedException, ParseException {
		Coupon temp= null;
		Set<Coupon> couponsFinal =new HashSet<>(); 
		Set<Coupon> coupons =new HashSet<>();
			coupons=(Set<Coupon>) daocompany.getCoupons();
		
		Iterator<Coupon> iter = coupons.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if (temp.getStartDate().before(date))
			{
				couponsFinal.add(temp);
			}
		}
		return couponsFinal;
	}
	/**
	 * method printCompany returns the whole company
	 * @return Company returns the company 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Company printCompany() throws ClassNotFoundException, SQLException, InterruptedException	
	{
			return daocompany.printCompany();
		
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
		
					if (daocompany.login(name, password))
					{
						this.loginId= daocompany.getLoginId();
						return this;
					}
					return null;
	}


}
