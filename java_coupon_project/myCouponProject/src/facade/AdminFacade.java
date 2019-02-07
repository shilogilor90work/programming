package facade;
import java.io.Serializable;
import java.sql.SQLException;

import java.text.ParseException;
import java.util.Collection;

import exceptions.DuplicateUserException;
import exceptions.MyExceptions;
import exceptions.WrongDataInputException;
import exceptions.NullPointerException;
import members.Company;
import dbdao.CompanyDBDAO;
import members.Customer;
import dbdao.CustomerDBDAO;
/**
 * The class AdminFacade is part of myCouponProject
 * Facade is a design pattern that is being used in myCouponProject
 * AdminFacade is the facade for the admin user
 * @author Oshra & Shilo
 *
 */
public class AdminFacade implements CouponClientFacade, Serializable{

	private CompanyDBDAO daocompany= new CompanyDBDAO();
	private CustomerDBDAO daocustomer= new CustomerDBDAO();
	private String admin="admin";
	private String password="1234";
	/**
	 * method createCompany creates a new company by calling CompanyDBDAO
	 * @see CompanyDBDAO
	 * @param Company company stores the company that is being created
	 * @throws DuplicateUserException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	
	public void createCompany(Company company) throws ClassNotFoundException, SQLException, InterruptedException, DuplicateUserException  
	{

			daocompany.createCompany(company);
	}
	/**
	 * method removeCompany removes a company by calling CompanyDBDAO
	 * @see CompanyDBDAO
	 * @param Company company stores the company that is being removed
	 * @throws ParseException 
	 * @throws NullPointerException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void removeCompany(Company company) throws ClassNotFoundException, SQLException, InterruptedException, NullPointerException, ParseException  
	{		
			daocompany.removeCompany(company);
		
	}

	/**
	 * method updateCompany updates an exsisting caompany by calling CompanyDBDAO
	 * @see CompanyDBDAO
	 * @param Company company stores the company that is being updated
	 * @throws InterruptedException 
	 * @throws WrongDataInputException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void updateCompany(Company company) throws ClassNotFoundException, SQLException, WrongDataInputException, InterruptedException  
	{
		
				daocompany.updateCompany(company);
			
		
	}

	/**
	 * method getCompany returns a company according to its id by calling CompanyDBDAO
	 * @see CompanyDBDAO
	 * @param long id stores the id of the company that needs to be returned
	 * @return returns Company with the matching id if not found returns null
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Company getCompany(long id) throws ClassNotFoundException, SQLException, InterruptedException  
	{
			return daocompany.getCompany(id);
		
	}

	/**
	 * method getAllCompanies returns all companies by calling CompanyDBDAO
	 * @see CompanyDBDAO
	 * @return returns Collection<Company> returns all companies if not found returns null
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Collection<Company> getAllCompanies() throws ClassNotFoundException, SQLException, InterruptedException 
	{
			return daocompany.getAllCompanies();
		
	}
	
	/**
	 * method createCustomer creates a new customer by calling CustomerDBDAO
	 * @see CustomerDBDAO
	 * @param Customer customer stores the customer that is being created
	 * @throws DuplicateUserException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void createCustomer(Customer customer) throws ClassNotFoundException, SQLException, InterruptedException, DuplicateUserException {
		
				daocustomer.createCustomer(customer);
		}

	/**
	 * method removeCustomer removes a customer by calling CustomerDBDAO
	 * @see CustomerDBDAO
	 * @param Customer customer stores the customer that is being removed
	 * @throws WrongDataInputException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void removeCustomer(Customer customer) throws ClassNotFoundException, SQLException, InterruptedException, WrongDataInputException{

				daocustomer.removeCustomer(customer);
	
	}

	/**
	 * method updateCustomer updates an existing customer by calling CustomerDBDAO
	 * @see CustomerDBDAO
	 * @param Customer customer stores the customer that is being updated
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws WrongDataInputException 
	 * @throws ClassNotFoundException 
	 */
	public void updateCustomer(Customer customer) throws ClassNotFoundException, WrongDataInputException, SQLException, InterruptedException{

				daocustomer.updateCustomer(customer);
		
	}

	/**
	 * method getCustomer returns a customer according to its id by calling CustomerDBDAO
	 * @see CustomerDBDAO
	 * @param long id stores the id of the customer that needs to be returned
	 * @return returns Customer with the matching id if not found returns null
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Customer getCustomer(long id) throws ClassNotFoundException, SQLException, InterruptedException {

		
				return daocustomer.getCustomer(id);
		
	}

	/**
	 * method getAllCustomers returns all customers by calling CustomerDBDAO
	 * @see CustomrDBDAO
	 * @return returns Collection<Customer> returns all customers if not found returns null
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Collection<Customer> getAllCustomers() throws ClassNotFoundException, SQLException, InterruptedException{
	
			return daocustomer.getAllCustomers();
	}
	/**
	 * this method logs in 
	 * @return if successful it returns itself otherwise returns null
	 */
	@Override
	public CouponClientFacade login(String name, String password, String clientType) {
		if (name.equals(this.admin) && password.equals(this.password))
		{
			return this;
		}
		return null;
	}
}