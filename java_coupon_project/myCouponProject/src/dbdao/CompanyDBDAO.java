package dbdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import backgroundFunctions.ConnectionPool;
import exceptions.DuplicateCouponTypeException;
import exceptions.DuplicateUserException;
import exceptions.WrongDataInputException;
import exceptions.NullPointerException;
import members.Company;
import members.Coupon;
import querys.CompanyQuery;
/**
 * The class CompanyDBDAO is part of myCouponProject
 * the class CompanyDBDAO communicates between the CompanyFacade and SQL
 * @see CompanyFacade
 * @author Oshra & Shilo
 */

public class CompanyDBDAO implements CompanyDAO {

	
	private long loginId;
	/**
	 * method createCompany creates a new company
	 * @param boolean check checks company
	 * @see checkCompany
 	 * @throws SQLException, ClassNotFoundException, InterruptedException 

	 */
	@Override
	public void createCompany(Company company) throws SQLException,  ClassNotFoundException, InterruptedException, DuplicateUserException {
		//checking if this company already exists
		boolean check=this.checkCompany(company);
		//getting connection
		Connection con= ConnectionPool.getInstance().getConnection();	
		if (check)
		{
	        ConnectionPool.getInstance().returnConnection(con);		
			throw new DuplicateUserException();
		}
		else
		{
	        String insertQuery = CompanyQuery.insertQuery(company.getCompName(), company.getPassword(), company.getEmail());
	        PreparedStatement insertStatement =  (PreparedStatement) con.prepareStatement(insertQuery);
	        insertStatement.execute();
		}
        ConnectionPool.getInstance().returnConnection(con);		

	}
	/**
	 * method checkCompany checks if company exists in the data base
	 * @param Company company stores the company that is being checked
	 * @return returns boolean true if the company exists and false if the company doesn't exist
	 * @throws SQLException, ClassNotFoundException, InterruptedException 
	 */	
	
	public boolean checkCompany(Company company) throws SQLException, ClassNotFoundException, InterruptedException 
	{
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet booleanResaultSet;
        booleanResaultSet = statement.executeQuery(CompanyQuery.companyFromNameQuery(company.getCompName()));
        // if there is such line so company already exists
        if (booleanResaultSet.next())
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
	
	/**
	 * method removeCompany removes a company
	 * @throws SQLException, ClassNotFoundException, InterruptedException, nullPointerException, ParseException 

	 */
	
	@Override
	public void removeCompany(Company company) throws SQLException, ClassNotFoundException, InterruptedException, NullPointerException, ParseException {
		CouponDBDAO daocoupon = new CouponDBDAO();
		Collection<Coupon> coupons =new HashSet<>(); 
		Coupon temp = null;
		// renewing company to make sure id is the same
		company = this.getCompanyByName(company.getCompName());
		this.loginId=company.getId();
		coupons= this.getCoupons();
		// starting iterater for removing all coupons of this company
		Iterator<Coupon> iter = coupons.iterator();
		while(iter.hasNext())
		{
			temp=iter.next();
			daocoupon.removeCoupon(temp);
		}
		// getting connection
		Connection con= ConnectionPool.getInstance().getConnection();	
        
		String deleteQuery = CompanyQuery.deleteCompanyFromIdQuery(company.getId());
        // deleting the company
		PreparedStatement statement =  (PreparedStatement) con.prepareStatement(deleteQuery);
        statement.execute();
        // returning connection
        ConnectionPool.getInstance().returnConnection(con);
	}
	
	/**
	 * method getCompanyByName gets a name and returns the company that has that name
	 * @param String compName stores the name of the company that needs to be returned
	 * @param ReasultSet resaultSet checks if a company with this name exists if it doesn't it returns null
	 * @return Company returns a Company with a matching name to the name it got
	 * @throws SQLException, ClassNotFoundException, InterruptedException, nullPointerException
	 */
	
	
	public Company getCompanyByName(String compName) throws SQLException, ClassNotFoundException, InterruptedException, NullPointerException 
	{
		long temp = 0;
		//getting connection
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CompanyQuery.companyFromNameQuery(compName));
        // getting the company by its name
        if (resaultSet.next())
        {
        	temp=resaultSet.getLong("ID");
            // returning connection
        	ConnectionPool.getInstance().returnConnection(con);
    		return this.getCompany(temp);
        }
        // returning connection
		ConnectionPool.getInstance().returnConnection(con);
		// if the program reaches here it means that there was no company with that name so it throws an exception
		throw new NullPointerException();
        
	}
	
	/**
	 * method updateCompany updates a company
	 * @param boolean check checks company
	 * @see checkCompany
	 * @throws  SQLException, WrongDataInputException, ClassNotFoundException, InterruptedException 

	 */
	
	
	@Override
	public void updateCompany(Company company) throws SQLException, WrongDataInputException, ClassNotFoundException, InterruptedException {
		// check if company exists
		boolean check=this.checkCompany(company);
		Connection con= ConnectionPool.getInstance().getConnection();	
		if (check==false)
		{
	        // returning connection
			ConnectionPool.getInstance().returnConnection(con);
			throw new WrongDataInputException();
		}
		else
		{
        String updateQuery = CompanyQuery.updateQuery(company.getPassword() , company.getEmail() ,company.getCompName());
        PreparedStatement statement =  (PreparedStatement) con.prepareStatement(updateQuery);
        statement.execute();
		}
        // returning connection
        ConnectionPool.getInstance().returnConnection(con);		
		
	}

	
	/**
	 * method getCompany returns a company
	 * @param boolean check checks company
	 * @see checkCompany
	 * @param long id stores the id of the company that needs to be returned
	 * @return Company company returns the company with the matching id
	 * @throws  SQLException, ClassNotFoundException, InterruptedException  

	 */
	
	
	@Override
	public Company getCompany(long id) throws SQLException, ClassNotFoundException, InterruptedException {
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CompanyQuery.companyFromIdQuery(id));
        resaultSet.next(); 
            long compid = resaultSet.getLong("id");
            String compName = resaultSet.getString("compName");
           String password = resaultSet.getString("password");
            String email = resaultSet.getString("email");
            // returning connection
        ConnectionPool.getInstance().returnConnection(con);
        Company company = new Company(compid, compName, password, email);
        return company;
	}

	/**
	 * method getAllCompanies returns all companies
	 * @param Set<Company> companys is a new HashSet containing all companies
	 * @return Collection<Company> companys returns all companies
	 * @throws  SQLException, ClassNotFoundException, InterruptedException  
	 */
	@Override
	public Collection<Company> getAllCompanies() throws SQLException, ClassNotFoundException, InterruptedException {
		Set<Company> companys =new HashSet<>(); 
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CompanyQuery.companyQuery());
        while(resaultSet.next())
        {
        	long Id = resaultSet.getLong("id");
        	Company temp = this.getCompany(Id);
        	companys.add(temp);
        }
        // returning connection
        ConnectionPool.getInstance().returnConnection(con);
      	return companys;
	}


	/**
	 * method getCoupons returns all of the companies coupons
	 * @param long id stores the coupon's ID
	 * @param Set<Coupon> coupons is a new HashSet containing all of the companies coupons
	 * @return Collection<Coupon> returns all of the companies coupons
	 * @throws  SQLException, ClassNotFoundException, InterruptedException, ParseException  

	 */
	
	@Override
	public Collection<Coupon> getCoupons() throws SQLException, ClassNotFoundException, InterruptedException, ParseException {
		CouponDBDAO couponDBDAO=new CouponDBDAO();
		long Id;
		Set <Coupon> coupons = new HashSet<>();
		Connection con= ConnectionPool.getInstance().getConnection();;
		Statement statement = (Statement) con.createStatement();
		ResultSet resaultSet;
		resaultSet = statement.executeQuery(CompanyQuery.companyCouponQuery(loginId));    
       // gets all ids of coupons that are from this company
		while (resaultSet.next())
        {
        	Id = resaultSet.getLong("COUPON_ID");
        	Coupon temp =couponDBDAO.getCoupon(Id);
            coupons.add(temp);
        }
        // returning connection
        ConnectionPool.getInstance().returnConnection(con);
		return coupons;	
   	}
	
	
	/**
	 * method login logs in
	 * @return boolean returns true if login succeeded and false if login failed 
	 *  @throws  SQLException, ClassNotFoundException, InterruptedException 
	 */
	
	@Override
	public boolean login(String compName, String password) throws SQLException, ClassNotFoundException, InterruptedException {
		Connection con= ConnectionPool.getInstance().getConnection();
		Statement statement = (Statement) con.createStatement();
        ResultSet resaultSet;
        resaultSet = statement.executeQuery(CompanyQuery.companyLoginQuery(compName , password));
        if (resaultSet.next())
        {
            // returning connection
        	ConnectionPool.getInstance().returnConnection(con);
        	this.loginId =resaultSet.getLong("id");
        	return true;
        }
        // returning connection
        ConnectionPool.getInstance().returnConnection(con);
		return false;
	}
	
	/**
	 * method printCompany returns the whole company
	 * @return Company returns the company 
	 * @throws SQLException, ClassNotFoundException, InterruptedException
	 */
	public Company printCompany() throws SQLException, ClassNotFoundException, InterruptedException 
	{
		return this.getCompany(loginId);
	}
	
	/**
	 * 
	 * @param Coupon coupon stores the coupon that is being created
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DuplicateCouponTypeException
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	
	public void createCoupon(Coupon coupon) throws ClassNotFoundException, SQLException, DuplicateCouponTypeException, InterruptedException, ParseException
	{
		CouponDBDAO daocoupon = new CouponDBDAO();
		// creates coupon
		daocoupon.createCoupon(coupon);
		// adds the connectipon between the company and the coupon
		daocoupon.registerCoupon(loginId, coupon);
	}
}
