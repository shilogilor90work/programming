package backgroundFunctions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import dbdao.CouponDBDAO;
import utilities.DateToStringAndVs;
/**
 * The class DailyCouponExperationTask is part of myCouponProject
 * The class DailyCouponExperationTask runs every day deleting any expired coupons
 * @author Oshra & Shilo
 */
public class DailyCouponExpirationTask implements Runnable {
private boolean quit=false;
CouponDBDAO couponDBDAO = new CouponDBDAO();
public Object key = new Object();
	/**
	 * method run runs every day and deletes all expired coupons
	 */
	@Override
	public  void run(){
		synchronized(key)
		{
		while(quit==false)
		{
			try
			{
				Date today = Calendar.getInstance().getTime();
				Connection con= ConnectionPool.getInstance().getConnection();	
				Statement statement = (Statement) con.createStatement();
		        ResultSet resaultSet;
		        resaultSet = statement.executeQuery("SELECT id FROM coupon WHERE end_date < '"+ DateToStringAndVs.dateToString(today)+"'");
		        while(resaultSet.next() && quit==false)
		        {
		        	long couponid=resaultSet.getLong("id");
		        	System.out.println("deleting couppoun "+couponid+" since its expired");
		        	couponDBDAO.removeCoupon(couponDBDAO.getCoupon(couponid));	
		        }
		        ConnectionPool.getInstance().returnConnection(con);		
			try {
				key.wait(60*60*24*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			} catch(SQLException | ClassNotFoundException | InterruptedException | ParseException e)
			{
				e.printStackTrace();

				System.out.println(e.getCause());
			}
			}
		}
	}
	/**
	 * method stopTask stops all tasks immediately
	 */
	public void stopTask()
	{
		quit=true;
		key.notify();
	}

}
