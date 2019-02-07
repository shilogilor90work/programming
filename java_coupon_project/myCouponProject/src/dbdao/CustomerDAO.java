package dbdao;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import exceptions.DuplicateUserException;
import exceptions.WrongDataInputException;
import members.Coupon;
import members.Customer;
/**
 * The interface CustomerDAO is part of myCouponProject
 * @author Oshra & Shilo
 *
 */
public interface CustomerDAO {

		public void createCustomer (Customer customer) throws SQLException, ClassNotFoundException, InterruptedException, DuplicateUserException;
		public void removeCustomer (Customer customer) throws SQLException, ClassNotFoundException, InterruptedException, WrongDataInputException;
		public void updateCustomer (Customer customer) throws WrongDataInputException, SQLException, ClassNotFoundException, InterruptedException;
		public Customer getCustomer (long id) throws SQLException, ClassNotFoundException, InterruptedException;
		public Collection <Customer> getAllCustomers() throws SQLException, ClassNotFoundException, InterruptedException;
		public Collection <Coupon> getCoupons() throws SQLException, ClassNotFoundException, InterruptedException, ParseException;
		public boolean login(String custName ,String password) throws SQLException, ClassNotFoundException, InterruptedException;
}
