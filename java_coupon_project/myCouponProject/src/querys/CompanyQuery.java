package querys;
/**
 * The class CompanyQuery is part of myCouponProject
 * The class CompanyQuery contains all the string queries for company
 * @author Oshra & Shilo
 *
 */
public class CompanyQuery {
	/**
	 * 
	 * 
	 * @param name is the name of thre company name
	 * @param password the company password
	 * @param email the company email
	 * @return the insert Query
	 */
	public static String insertQuery(String name, String password,String email)
	{
		return "INSERT INTO company (compName,password,email) VALUES ('"+ name+"','"+password+"','"+email+"')";
	}
	/**
	 * 
	 * @param name the name of the company
	 * @return the Query
	 */
	
	public static String companyFromNameQuery(String name)
	{
		return "SELECT * FROM company WHERE CompName='"+name+"'";
	}
	/**
	 * 
	 * @param id id of the company
	 * @return the delete Query
	 */
	public static String deleteCompanyFromIdQuery(long id)
	{
		return "DELETE FROM company WHERE id="+ id;
	}
	/**
	 * 
	 * @param password password of the company
	 * @param email the email of the company
	 * @param name the name of the company
	 * @return the update Query
	 */
	public static String updateQuery( String password , String email , String name)
	{
		return "UPDATE company SET  password= '"+password+"',email='"+email+"' WHERE compName='"+ name+"'";
	}
	/**
	 * 
	 * @param id the id of the company 
	 * @return the Query
	 */
	public static String companyFromIdQuery(long id)
	{
		return "SELECT * FROM company WHERE id="+id;
	}
	/**
	 * 
	 * @return the Query for all companies
	 */
	public static String companyQuery()
	{
		return "SELECT * FROM company";
	}
	/**
	 * 
	 * @param id the id of the company
	 * @return the Query
	 */
	public static String companyCouponQuery(long id)
	{
		return "SELECT COUPON_ID FROM Company_Coupon WHERE COMP_ID="+id;
	}
	/**
	 * 
	 * @param name the name of the company
	 * @param password the password of the company
	 * @return the Query for login
	 */
	public static String companyLoginQuery(String name, String password)
	{
		return "SELECT ID FROM Company WHERE compName ='"+name+ "' AND password='" +password+"'";
	}
}
