package querys;

import java.util.Date;
/**
 * The class CouponQuery is part of myCouponProject
 * The class CouponQuery contains all the string queries for coupon
 * @author Oshra & Shilo
 *
 */

import utilities.CouponType;
import utilities.DateToStringAndVs;
public class CouponQuery {
	/**
	 * 
	 * @param title the title of the coupon
	 * @param start_date the start date of the coupon
	 * @param end_date the end date of the coupon
	 * @param amount the amount of coupons available to buy
	 * @param type the type of coupon
	 * @param message message givin by the company about the coupon
	 * @param price the cost of the coupon
	 * @param image the image that goes with this coupon
	 * @return the insert quety
	 */
	public static String insertQuery(String title,Date start_date, Date end_date,int amount,CouponType type, String message,double price, String image)
	{
		return "INSERT INTO coupon (title,start_date,end_date,amount,type,message,price,image)"
        		+ " VALUES ('"+title+"','"+DateToStringAndVs.dateToString(start_date)
        		+"','"+DateToStringAndVs.dateToString(end_date)
        		+"',"+amount+
        		",'"+type.toString()
        		+"','"+message
        		+"',"+price+
        		",'"+image+"')";
	}
	/**
	 * 
	 * @param title the title of the coupon
	 * @return  the query
	 */
	public static String couponFromTitleQuery(String title)
	{
		return "SELECT * FROM coupon WHERE title='"+ title +"'";
	}
	/**
	 * 
	 * @param id the id of the coupon
	 * @return the query
	 */
		
	public static String deleteCouponFromIdQuery(long id)
	{
		return "DELETE FROM coupon WHERE id="+ id;
	}
	/**
	 * 
	 * @param id the id of the coupon
	 * @return the query
	 */
	public static String deleteCompanyCouponFromIdQuery(long id)
	{
		return "DELETE FROM company_coupon WHERE coupon_id="+ id;
	}
	/**
	 * 
	 * @param id the id of the coupon
	 * @return the query
	 */
	public static String deleteCustomerCouponFromIdQuery(long id)
	{
		return "DELETE FROM customer_coupon WHERE coupon_id="+ id;
	}
	
	/**
	 * 
	 * @param end_date the updated end date for the query
	 * @param price the new price of the query
	 * @param title the title
	 * @return the query for update
	 */
	public static String updateQuery( Date end_date , double price , String title)
	{
		return  "UPDATE coupon SET end_date='"+DateToStringAndVs.dateToString(end_date)+"', price="+price+
		        " WHERE title ='"+ title+"'";
	}
	/**
	 * 
	 * @param id the id of the coupon
	 * @return the query 
	 */
	public static String CouponFromIdQuery(long id)
	{
		return "SELECT * FROM coupon WHERE id="+id;
	}
	/**
	 * 
	 * @return the query id from coupon
	 */
	public static String couponQuery()
	{
		return "SELECT ID FROM coupon";
	}
	/**
	 * 
	 * @param idCompany the id of the company creating the coupon
	 * @param idCoupon the id of the coupon
	 * @return the query to insert the connection between the company and coupon
	 */
	public static String insertCompanyCouponQuery(long idCompany, long idCoupon)
	{
		return "INSERT INTO COMPANY_COUPON (COMP_ID,COUPON_ID) VALUES ("+idCompany+","+idCoupon+")";
	}
	/**
	 * 
	 * @param couponType the coupon type asked for
	 * @return the query of for the list of coupon that have that coupon type
	 */
	public static String idFromCouponTypeQuery(String couponType)
	{
		return "SELECT id FROM coupon where Type='"+couponType+"'";
	}
}
