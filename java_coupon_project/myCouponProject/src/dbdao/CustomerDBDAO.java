package dbdao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import backgroundFunctions.ConnectionPool;
import exceptions.CustomerHasCouponAlready;
import exceptions.DuplicateUserException;
import exceptions.UnavailableCouponException;
import exceptions.WrongDataInputException;
import members.Coupon;
import members.Customer;
import querys.CustomerQuery;
/**
 * The class CustomerDBDAO is part of myCouponProject
 * the class CustomerDBDAO communicates between the CustomerFacade and SQL
 * @author Oshra & Shilo
 * @see CustomerFacade
 */
public class CustomerDBDAO implements CustomerDAO {

	private long loginId;
	/**
	 * method createCustomer creates a new customer
	 * @param boolean check  if customer is already in the system
	 * @see checkCustomer
	 * @throws  SQLException, ClassNotFoundException, InterruptedException, DuplicateUserException 
	 */
	@Override
	public void createCustomer(Customer customer) throws  SQLException, ClassNotFoundException, InterruptedException, DuplicateUserException {
		boolean check=this.checkCustomer(customer);
		Connection con= ConnectionPool.getInstance().getConnection();	
		if (check)
		{
			ConnectionPool.getInstance().returnConnection(con);			
			throw new DuplicateUserException();
		}
		else
		{
		        String insertQuery = CustomerQuery.insertQuery(customer.getCustName() , customer.getPassword());
	        PreparedStatement statement =  (PreparedStatement) con.prepareStatement(insertQuery);
	        statement.execute();
		}
		ConnectionPool.getInstance().returnConnection(con);			
	}
	
	/**
	 * 
	 * method purchaseCoupon lets the customer purchase a new coupon
	 * @param Coupon coupon is the coupon that the customer wants to purchase
	 * @param ResultSet resaultSet1 checks if the customer already owns this coupon (can't own duplicates)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws CustomerHasCouponAlready
	 * @throws UnavailableCouponException
	 * @throws ParseException
	 */
	public void purchaseCoupon(Coupon coupon) throws SQLException, ClassNotFoundException, InterruptedException, CustomerHasCouponAlready, UnavailableCouponException, ParseException 
	{
		 CouponDBDAO daocoupon = new CouponDBDAO();
		 coupon=daocoupon.getCouponByName(coupon.getTitle());
		Connection con= ConnectionPool.getInstance().getConnection();	
		Statement statement1 = (Statement) con.createStatement();
		ResultSet resaultSet1;
		resaultSet1 = statement1.executeQuery(CustomerQuery.customerCouponFromIdQuery(loginId , coupon.getId()));
		if (resaultSet1.next())
		{
			ConnectionPool.getInstance().returnConnection(con);			
			throw new CustomerHasCouponAlready();		
		}
		else
		{
			Statement statement2 = (Statement) con.createStatement();
			ResultSet resaultSet2;
			resaultSet2 = statement2.executeQuery(CustomerQuery.couponFromIdWithAmountQuery(coupon.getId()));
			if (resaultSet2.next())
			{
				 String updateQuery = CustomerQuery.updateAmountQuery(resaultSet2.getInt("amount") , coupon.getId());
			        PreparedStatement statement3 =  (PreparedStatement) con.prepareStatement(updateQuery);
			        statement3.execute();		
			     String insertQuery=CustomerQuery.insertCustomerCouponQuery(loginId , coupon.getId());
			        PreparedStatement statement4 =  (PreparedStatement) con.prepareStatement(insertQuery);
			        statement4.execute();			
			}
			else 	
			{
				ConnectionPool.getInstance().returnConnection(con);			
				throw new UnavailableCouponException();
			}

		}
		ConnectionPool.getInstance().returnConnection(con);	
	
	}
	/**
	 * method removeCustomer removes a customer
	 * @param Customer customer stores the customer that is being deleted
	 * @param deleteCustomer deletes the customer from the customer table
	 * @param deleteCustomerCoupon deletes all the customer's coupons from the customer's coupon table
	  
	 */
	
	@Override
	public void removeCustomer(Customer customer) throws SQLException, ClassNotFoundException, InterruptedException, WrongDataInputException{
		customer = this.getCustomerByName(customer.getCustName());
		Connection con= ConnectionPool.getInstance().getConnection();	
        String deleteCustomer =CustomerQuery.deleteCustomerByIdQuery(customer.getId());
        String deleteCustomerCoupon = CustomerQuery.deleteCustomerCouponFromIdQuery(customer.getId());
        
        PreparedStatement statement =  (PreparedStatement) con.prepareStatement(deleteCustomer);
        statement.execute();
       
        statement =  (PreparedStatement) con.prepareStatement(deleteCustomerCoupon);
        statement.execute();
        ConnectionPool.getInstance().returnConnection(con);	
     			
	}
	
	/**
	 * 
	  * method getCustomerByName gets a name and returns the customer that has that name
	 * @param String custName stores the name of the customer that needs to be returned
	 * @param ReasultSet resaultSet checks if a customer with this name exists if it doesn't it returns null
	 * @return Customer returns a Customer with a matching name to the name it got
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws WrongDataInputException
	 */
	
	public Customer getCustomerByName(String custName) throws SQLException, ClassNotFoundException, InterruptedException, WrongDataInputException
	{
		long temp = 0;
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CustomerQuery.customerFromNameQuery(custName));
        if (resaultSet.next())
        {
        	temp=resaultSet.getLong("ID");
        	ConnectionPool.getInstance().returnConnection(con);
    		return this.getCustomer(temp);
        }
        else
        {
           	ConnectionPool.getInstance().returnConnection(con);
			throw new WrongDataInputException();
        }
        
	}
	

	/**
	 * method updateCustomer updates a customer
	 * @param boolean check checks if customer is in the system
	 * @see checkCustomer
	 */	

	@Override
	public void updateCustomer(Customer customer) throws WrongDataInputException, SQLException, ClassNotFoundException, InterruptedException {
		boolean check=this.checkCustomer(customer);
		Connection con= ConnectionPool.getInstance().getConnection();	
		if (check==false)
		{
	       	ConnectionPool.getInstance().returnConnection(con);
			throw new WrongDataInputException();
		}
		else
		{
		
        String updateQuery = CustomerQuery.updateCustomerQuery(customer.getPassword() , customer.getCustName());
        PreparedStatement statement =  (PreparedStatement) con.prepareStatement(updateQuery);
        statement.execute();
		}
		ConnectionPool.getInstance().returnConnection(con);		
		
	}

	
	 /**
	 * method getCustomer returns a customer according to an ID
	 * @see checkCustomer
	 * @param long id stores the id of the customer that needs to be returned
	 * @return Customer customer returns the customer with the matching id
	 */
	@Override
	public Customer getCustomer(long id) throws SQLException, ClassNotFoundException, InterruptedException {
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CustomerQuery.customerFromIdeQuery(id));
        resaultSet.next(); 
            long custid = resaultSet.getLong("id");
            String custName = resaultSet.getString("custName");
           String password = resaultSet.getString("password");
        ConnectionPool.getInstance().returnConnection(con);
        Customer customer = new Customer(custid, custName, password);
        return customer;
	}
	/**
	 * method getAllCustomers returns all customers
	 * @param Set<Customer> customers is a new HashSet containing all customers
	 * @return Collection<Customer> customers returns all customers
	
	 */
	@Override
	public Collection<Customer> getAllCustomers() throws SQLException, ClassNotFoundException, InterruptedException {
		Set<Customer> customers =new HashSet<>(); ;
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CustomerQuery.idFromCustomerQuery());
        while(resaultSet.next())
        {
        	long Id = resaultSet.getLong("id");
        	Customer temp = this.getCustomer(Id);
        	customers.add(temp);
        }
        ConnectionPool.getInstance().returnConnection(con);
      	return customers;	}
	
	/**
	 * method getCoupons returns all of the customer's coupons
	 * @param long idCoupon stores the coupon's ID
	 * @param Set<Coupon> coupons is a new HashSet containing all of the customer's coupons
	 * @return Collection<Coupon> returns all of the customer's coupons
	 */
	@Override
	public Collection<Coupon> getCoupons() throws SQLException, ClassNotFoundException, InterruptedException, ParseException {
		CouponDBDAO couponDBDAO=new CouponDBDAO();
		long idCoupon;
		Set <Coupon> coupons = new HashSet<>();
		Connection con= ConnectionPool.getInstance().getConnection();;
		Statement statement = (Statement) con.createStatement();
		ResultSet resaultSet;
		resaultSet = statement.executeQuery(CustomerQuery.couponCustomerQuery(loginId));    
        while (resaultSet.next())
        {
        	idCoupon = resaultSet.getLong("COUPON_ID");
        	Coupon temp =couponDBDAO.getCoupon(idCoupon);
            coupons.add(temp);
        }
        ConnectionPool.getInstance().returnConnection(con);
		return coupons;	
	}
	
	
	
	public Collection<Coupon> getAllCoupons() throws SQLException, ClassNotFoundException, InterruptedException, ParseException {
		CouponDBDAO couponDBDAO=new CouponDBDAO();
		long idCoupon;
		Set <Coupon> coupons = new HashSet<>();
		Connection con= ConnectionPool.getInstance().getConnection();;
		Statement statement = (Statement) con.createStatement();
		ResultSet resaultSet;
		resaultSet = statement.executeQuery(CustomerQuery.getAllCouponQuery());    
        while (resaultSet.next())
        {
        	idCoupon = resaultSet.getLong("ID");
        	Coupon temp =couponDBDAO.getCoupon(idCoupon);
            coupons.add(temp);
        }
        ConnectionPool.getInstance().returnConnection(con);
		return coupons;	
	}
	
	
	public Collection<Coupon> getAllCouponsByType(String type) throws SQLException, ClassNotFoundException, InterruptedException, ParseException {
		CouponDBDAO couponDBDAO=new CouponDBDAO();
		long idCoupon;
		Set <Coupon> coupons = new HashSet<>();
		Connection con= ConnectionPool.getInstance().getConnection();;
		Statement statement = (Statement) con.createStatement();
		ResultSet resaultSet;
		resaultSet = statement.executeQuery(CustomerQuery.getAllCouponsByTypeQuery(type));    
        while (resaultSet.next())
        {
        	idCoupon = resaultSet.getLong("ID");
        	Coupon temp =couponDBDAO.getCoupon(idCoupon);
            coupons.add(temp);
        }
        ConnectionPool.getInstance().returnConnection(con);
		return coupons;	
	}
	/**
	 * method login logs in
	 * @return boolean returns true if login succeeded and false if login failed 
	 */
	@Override
	public boolean login(String custName, String password) throws SQLException, ClassNotFoundException, InterruptedException{
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CustomerQuery.loginQuery(custName , password));
        if (resaultSet.next())
        {
        	this.loginId =resaultSet.getLong("id");
        	ConnectionPool.getInstance().returnConnection(con);
        	return true;
        }
        ConnectionPool.getInstance().returnConnection(con);
		return false;
	}
	
	/**
	 * 
	  * method checkCustomerToCoupon checks if a customer owns a specific coupon 
	 * @param long customer stores the customer's ID
	 * @param long coupon stores the coupon's ID
	 * @return returns boolean true if the customer already has this coupon and false if the customer doesn't have this coupon
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public boolean checkCustomerToCoupon(long customerId,long couponId) throws SQLException, ClassNotFoundException, InterruptedException 
	{
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CustomerQuery.checkCustomerCouponQuery(customerId ,couponId));
        if (resaultSet.next())
        {
           	ConnectionPool.getInstance().returnConnection(con);
        	return true;
        }
       	ConnectionPool.getInstance().returnConnection(con);
        return false;
	}
	/**
	 * 
	 * method checkCustomer checks if customer exists in the data base
	 * @param Customer customer stores the customer that is being checked
	 * @return returns boolean true if the customer exists and false if the customer doesn't exist
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public boolean checkCustomer(Customer customer) throws SQLException, ClassNotFoundException, InterruptedException 
	{
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CustomerQuery.customerFromNameQuery(customer.getCustName()));
        if (resaultSet.next())
        {
           	ConnectionPool.getInstance().returnConnection(con);
        	return true;
        }
       	ConnectionPool.getInstance().returnConnection(con);
        return false;
	}
	/**
	 * 
	 * @return long loginId
	 */
	public long getLoginId() {
		return loginId;
	}

	
	
}
