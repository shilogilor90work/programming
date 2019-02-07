package dbdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import backgroundFunctions.ConnectionPool;
import exceptions.DuplicateCouponTypeException;
import exceptions.WrongDataInputException;
import members.Coupon;
import utilities.CouponType;
import utilities.DateToStringAndVs;
import querys.CouponQuery;


/**
 * The class CouponDBDAO is part of myCouponProject
 * the class CouponDBDAO communicates between the CouponFacade and SQL
 * @author Oshra & Shilo
 * @see CouponFacade
 */
public class CouponDBDAO implements CouponDAO{

	/**
	 * method createCoupon creates a new coupon
	 * @param boolean check checks if coupon already exists
	 * @see checkCoupon
	 */
	
	@Override
	public void createCoupon(Coupon coupon) throws SQLException, DuplicateCouponTypeException, ClassNotFoundException, InterruptedException {	
		boolean check=this.checkCoupon(coupon);
		Connection con= ConnectionPool.getInstance().getConnection();	
		if (check)
		{
			ConnectionPool.getInstance().returnConnection(con);
			throw new DuplicateCouponTypeException();
		}
		else
		{
			System.out.println(coupon);
	        String insertQuery = CouponQuery.insertQuery(coupon.getTitle() , coupon.getStartDate() , coupon.getEndDate()
	        		, coupon.getAmount() , coupon.getType() , coupon.getMessage() , coupon.getPrice() , coupon.getImage());
	        PreparedStatement statement =  (PreparedStatement) con.prepareStatement(insertQuery);
	        statement.execute();

		}
		 ConnectionPool.getInstance().returnConnection(con);
	}
	
	/**
	 * 
	  * method checkCoupon checks if coupon exists in the data base
	 * @param Coupon coupon stores the coupon that is being checked
	 * @return returns boolean true if the coupon exists and false if the coupon doesn't exist
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public boolean checkCoupon(Coupon coupon) throws SQLException, ClassNotFoundException, InterruptedException 
	{
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CouponQuery.couponFromTitleQuery(coupon.getTitle()));
        if (resaultSet.next())
        {
   		 ConnectionPool.getInstance().returnConnection(con);
        	return true;
        }
		 ConnectionPool.getInstance().returnConnection(con);
        return false;
	}
	
	/**
	 * method removeCoupon removes a coupon
	 * @param coupon stores the coupon that is being deleted
	 * @param deleteCoupon deletes the coupon from the coupon table
	 * @param deleteCompanyCoupon deletes the coupon from the company's coupon table
	 * @param deleteCustomerCoupon deletes the coupon from the customer's coupon table
	 */
	@Override
	public void removeCoupon(Coupon coupon) throws SQLException, ClassNotFoundException, InterruptedException, ParseException {
		coupon=this.getCouponByName(coupon.getTitle());
		Connection con= ConnectionPool.getInstance().getConnection();	
        String deleteCoupon = CouponQuery.deleteCouponFromIdQuery(coupon.getId());
        String deleteCompanyCoupon = CouponQuery.deleteCompanyCouponFromIdQuery(coupon.getId());
        String deleteCustomerCoupon = CouponQuery.deleteCustomerCouponFromIdQuery(coupon.getId());      
        PreparedStatement statement =  (PreparedStatement) con.prepareStatement(deleteCoupon);
        statement.execute();

        statement =  (PreparedStatement) con.prepareStatement(deleteCompanyCoupon);
        statement.execute();

        statement =  (PreparedStatement) con.prepareStatement(deleteCustomerCoupon);
        statement.execute();
        
        ConnectionPool.getInstance().returnConnection(con);	
	}
	/**
	 * method updateCoupon updates a coupon
	 * @param boolean check checks coupon
	 * @see checkCoupon
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws SQLException, WrongDataInputException, ClassNotFoundException, InterruptedException {
		boolean check=this.checkCoupon(coupon);
		Connection con= ConnectionPool.getInstance().getConnection();	
		if (check==false)
		{
			throw new WrongDataInputException();
		}
		else
		{
        String updateQuery =CouponQuery.updateQuery(coupon.getEndDate() , coupon.getPrice() , coupon.getTitle());
        PreparedStatement statement =  (PreparedStatement) con.prepareStatement(updateQuery);
        statement.execute();
		}
        ConnectionPool.getInstance().returnConnection(con);	
		
	}
	/**
	 * method getCoupon gets an ID and returns the coupon that has that ID
	 * @param long id stores the ID of the coupon that needs to be returned
	 * @return Coupon returns a Coupon with a matching ID to the ID it got
	 * @throws Exception
	 */
	@Override
	public Coupon getCoupon(long id) throws SQLException, ClassNotFoundException, InterruptedException, ParseException {	
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CouponQuery.CouponFromIdQuery(id));
        resaultSet.next(); 
            long coupid = resaultSet.getLong("id");
            String title = resaultSet.getString("title");
            Date startDate = DateToStringAndVs.stringToDate(resaultSet.getString("start_Date"));
            Date endDate = DateToStringAndVs.stringToDate(resaultSet.getString("end_Date"));
            int amount = resaultSet.getInt("amount");
            CouponType type =CouponType.valueOf(resaultSet.getString("type").trim());
            String message = resaultSet.getString("message");
            double price = resaultSet.getDouble("price");
            String image = resaultSet.getString("image");
        ConnectionPool.getInstance().returnConnection(con);
        Coupon coupon = new Coupon(coupid, title, startDate, endDate, amount, type, message, price, image);
        return coupon;
}
	/**
	 * method getAllCoupons returns all coupons
	 * @param Set<Coupon> coupons is a new HashSet containing all coupons
	 * @return Collection<Coupon> coupons returns all coupons
	 */
	@Override
	public Collection<Coupon> getAllCoupons() throws SQLException, ClassNotFoundException, InterruptedException, ParseException {
		Set<Coupon> coupons =new HashSet<>(); ;
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CouponQuery.couponQuery());
        while(resaultSet.next())
        {
        	long Id = resaultSet.getLong("id");
        	Coupon temp = this.getCoupon(Id);
        	coupons.add(temp);
        }
        ConnectionPool.getInstance().returnConnection(con);
      	return coupons;
		
	}
	
	/**
	 * 
	 * method getCouponByName gets a name and returns the coupon that has that name
	 * @param String title stores the name of the coupon that needs to be returned
	 * @return Coupon returns a Coupon with a matching name to the name it got
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public Coupon getCouponByName(String title) throws SQLException, ClassNotFoundException, InterruptedException, ParseException
	{
		long temp = 0;
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CouponQuery.couponFromTitleQuery(title));
        if (resaultSet.next())
        {
        	temp=resaultSet.getLong("ID");
        	ConnectionPool.getInstance().returnConnection(con);
    		return this.getCoupon(temp);
        }
        
       	ConnectionPool.getInstance().returnConnection(con);
       	throw new NullPointerException();
        
	}
	/**
	 * 
	 * method registerCoupon registers a new coupon into a company
	 * @param boolean check checks coupon
	 * @see checkCoupon
	 * @param long id stores the ID for the company in which we are registering a coupon
	 * @param Coupon coupon stores the coupon that is being added to the company
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void registerCoupon(long id, Coupon coupon) throws SQLException, ClassNotFoundException, InterruptedException, ParseException
	{
		boolean check=this.checkCoupon(coupon);
		Connection con= ConnectionPool.getInstance().getConnection();	
		if (check==false)
		{
			
		}else
		{
        String query =CouponQuery.insertCompanyCouponQuery(id , this.getCouponByName(coupon.getTitle()).getId());
        PreparedStatement statement =  (PreparedStatement) con.prepareStatement(query);
        statement.execute();
		}
		ConnectionPool.getInstance().returnConnection(con);
        
	}
	/**
	 * method getCouponByType gets a type and returns all coupons that match that type
	 * @param String couponType stores the type of coupon that need to be returned
	 * @return Collection<Coupon> returns all Coupons that match the type it got
	 * @throws Exception
	 */
	@Override
	public Collection<Coupon> getCouponByType(String couponType) throws SQLException, ClassNotFoundException, InterruptedException, ParseException{
		long Id;
		Set<Coupon> coupons =new HashSet<>(); ;
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CouponQuery.idFromCouponTypeQuery(couponType));
        while(resaultSet.next())
        {
        	 Id = resaultSet.getLong("id");
        	Coupon temp = this.getCoupon(Id);
        	coupons.add(temp);
        }
        ConnectionPool.getInstance().returnConnection(con);
      	return coupons;	}

}
