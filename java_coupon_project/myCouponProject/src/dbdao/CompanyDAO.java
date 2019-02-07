package dbdao;



import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import exceptions.DuplicateUserException;
import exceptions.WrongDataInputException;
import exceptions.NullPointerException;
import members.Company;
import members.Coupon;
/**
 * The interface CompanyDAO is part of myCouponProject
 * @author Oshra & Shilo
 *
 */
public interface CompanyDAO {
public void createCompany (Company company) throws SQLException, ClassNotFoundException, InterruptedException, DuplicateUserException ;
public void removeCompany (Company company) throws SQLException, ClassNotFoundException, InterruptedException, NullPointerException, ParseException ;
public void updateCompany (Company company) throws SQLException, WrongDataInputException, ClassNotFoundException, InterruptedException ;
public Company getCompany (long id) throws SQLException, ClassNotFoundException, InterruptedException ;
public Collection <Company> getAllCompanies() throws SQLException, ClassNotFoundException, InterruptedException;
public Collection <Coupon> getCoupons() throws SQLException, ClassNotFoundException, InterruptedException, ParseException ;
public boolean login(String compName ,String password) throws SQLException, ClassNotFoundException, InterruptedException ;
}
