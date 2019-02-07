package backgroundFunctions;
import java.sql.SQLException;

import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CouponClientFacade;
import facade.CustomerFacade;
/**
 * The class CouponSystem is part of myCouponProject
 * the class CouponSystem 
 * @author Oshra & Shilo
 */
public class CouponSystem {
	private static CouponSystem instance =null;
	private AdminFacade adminFacade = new AdminFacade();
	private CompanyFacade companyFacade = new CompanyFacade();
	private CustomerFacade customerFacade = new CustomerFacade();
	DailyCouponExpirationTask dailyTask = new DailyCouponExpirationTask();
	Thread dayly = new Thread(dailyTask,"name");
	/**
	 * method CouponSystem starts the dailytask
	 * @see DailyCouponExpirationTask
	 */
	private CouponSystem() 
	{
		dayly.start();
	}
	/**
	 * method getInstance returns an instance
	 * @return returns an instance
	 */
	public static synchronized  CouponSystem getInstance() 
	{
		if (instance==null)
			instance = new CouponSystem();
		return instance;
	}
	/**
	 * method login logs in 
	 * @param String name stores the login name
	 * @param String password stores the login password
	 * @param String type stores the login type
	 * @return returns the type of login admin customer of company
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */

	public CouponClientFacade login(String name, String password, String type) throws ClassNotFoundException, SQLException, InterruptedException
	{
		switch (type)
		{
			case "admin":
			{
				return adminFacade.login(name, password, type);
				
			}
			case "customer":
			{
				return customerFacade.login(name, password, type);
			}
			case "company":
			{
				return companyFacade.login(name, password, type);
			}
			default:System.out.println("sorry wrong type of user");
			return null;
		}
	}
	/**
	 * method shutDown shuts down the program
	 */
	public void shutDown()  
	{
        try {
			ConnectionPool.getInstance().closeAllConnections();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getCause());
		}	
        dailyTask.stopTask();
    }
}
