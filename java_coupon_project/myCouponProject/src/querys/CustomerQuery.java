package querys;
/**
 * The class CustomerQuery is part of myCouponProject
 * The class CustomerQuery contains all the string queries for customer
 * @author Oshra & Shilo
 *
 */
public class CustomerQuery {
	/**
	 * the method instertQuery returns the statement of inserting into customer table
	 * @param String name stores the name that is being inserted into the Customer table
	 * @param String password stores the password that is being inserted into the Customer table
	 * @return returns the String that is being inserted into query
	 */
	public static String insertQuery(String name, String password)
	{
		return "INSERT INTO Customer ( custName,password)"
        		+ " VALUES ('"+ name+"','" +password+"')";
	}
	/**
	 * 
	 * @param customerId the id of the customer 
	 * @param couponId the id of the coupon beeing bought
	 * @return the query for inserting of the purchase coupon
	 */
	
	public static String customerCouponFromIdQuery(long customerId , long couponId)
	{
		return "SELECT * FROM customer_coupon where cust_id= "+customerId+" and coupon_id = "+couponId;
	}
	/**
	 * 
	 * @param id the id of the customer
	 * @return the query
	 */
	public static String couponFromIdWithAmountQuery(long id)
	{
		return "SELECT * FROM coupon where id= "+id +" AND amount > "+0;
	}
	/**
	 * 
	 * @param amount the amount of coupons left to buy
	 * @param id the id of the coupon 
	 * @return the query
	 */
	public static String updateAmountQuery( int amount , long id)
	{
		return  "UPDATE coupon SET amount="+ (amount-1) +" WHERE id="+id;
	}
	/**
	 * 
	 * @param idCustomer the id of the customer
	 * @param idCoupon the id of the coupon
	 * @return returns the query to insert the purchase
	 */
	public static String insertCustomerCouponQuery(long idCustomer, long idCoupon)
	{
		return "INSERT INTO customer_coupon (cust_id, coupon_id) VALUES ( "+idCustomer+" , "+ idCoupon+" )";
	}
	/**
	 * 
	 * @param id the id of the customer
	 * @return returns the query to delete the customer
	 */
	public static String deleteCustomerByIdQuery(long id)
	{
		return "DELETE FROM Customer WHERE id="+ id;
	}
	/**
	 * 
	 * @param id the id of the customer
	 * @return returns the query to delete all customer coupons
	 */
	public static String deleteCustomerCouponFromIdQuery(long id)
	{
		return "DELETE FROM Customer_coupon WHERE cust_id="+ id;
	}
	/**
	 * 
	 * @param name name is the name of the customer
	 * @return returns the query
	 */
	public static String customerFromNameQuery(String name)
	{
		return "SELECT * FROM customer where custname='"+name+"'";
	}
	/**
	 * 
	 * @param password the password of the customer
	 * @param name name is thre name of the customer
	 * @return returns the query for upddating a customer
	 */
	public static String updateCustomerQuery(String password, String name)
	{
		return "UPDATE customer SET password='"
        		+password+"' WHERE custName='"+ name+"'";
	}
	/**
	 * 
	 * @param id the id od the customer
	 * @return returns the query
	 */
	public static String customerFromIdeQuery(long id)
	{
		return "SELECT * FROM customer WHERE id="+id;
	}
	/**
	 * 
	 * @return returns the query for id of customers
	 */
	public static String idFromCustomerQuery()
	{
		return "SELECT ID FROM Customer";
	}
	/**
	 * 
	 * @param id the id of the the customer
	 * @return returns the query 
	 */
	
	public static String couponCustomerQuery(long id)
	{
		return "SELECT * FROM Customer_Coupon WHERE CUST_ID="+id;
	}
	
	
	
	public static String getAllCouponQuery()
	{
		return "SELECT ID FROM coupon WHERE amount > 0 ";
	}
	
	public static String getAllCouponsByTypeQuery(String type)
	{
		return "SELECT ID FROM coupon WHERE Type='"+type+"'"+" AND amount > 0 ";
	}
	/**
	 * 
	 * @param name the name of the customer
	 * @param password the password of the customer
	 * @return returns the query for login
	 */
	public static String loginQuery(String name , String password)
	{
		return "SELECT ID FROM customer WHERE custname ='"+name+ "' AND password='" +password+"'";
	}
	/**
	 * 
	 * @param customerId the customer id 
	 * @param couponId the coupon id 
	 * @return returns the query for checking if the customer already has this coupon
	 */
	public static String checkCustomerCouponQuery(long customerId, long couponId)
	{
		return "SELECT * FROM customer_coupon WHERE cust_id="+customerId+" and coupon_id = "+couponId;
	}	
}
