package members;


import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * The class Company is part of myCouponProject
 * the class Company contains the basic description of a company
 * @author Oshra & Shilo
 *
 */
@XmlRootElement
public class Company implements Serializable{
private long id;
private String compName;
private String password;
private String email;
private Collection<Coupon> coupons;
/**
 * this constructor creates a new Company that has coupons
 * @param long id stores the company's ID
 * @param String compName stores the company's name
 * @param String password stores the company's password
 * @param String email stores the company's email
 * @param Coupon coupons stores the company's coupons
 */

public Company(long id, String compName, String password, String email, Collection<Coupon> coupons) {
	super();
	this.id = id;
	this.compName = compName;
	this.password = password;
	this.email = email;
	this.coupons = coupons;
}
public Company(){}
/**
 * this constructor creates a new Company that does not have any coupons
 *  @param Long id stores the company's ID
 * @param String compName stores the company's name
 * @param String password stores the company's password
 * @param String email stores the company's email
 */
public Company(long id, String compName, String password, String email) {
	super();
	this.id = id;
	this.compName = compName;
	this.password = password;
	this.email = email;
}
/**
 * addCoupon method adds a new coupon to the company's coupons 
 * @param coupon stores the Coupon that is being added to the coupons list
 */
public void addCoupon(Coupon coupon)
{
	coupons.add(coupon);
}
/**
 * removeCoupon method removes a specific coupon from the Company's existing coupons
 * @param coupon stores the Coupon that is being removed
 */
public void removeCoupon(Coupon coupon)
{
	coupons.remove(coupon);
}
/**
 * getId method returns the company's ID
 * @return long returns the company's ID 
 */
public long getId() {
	return id;
}
/**
 * setId method adds content into the company's ID
 * @param id stores the long that is being put into the company's ID
 */
public void setId(long id) {
	this.id = id;
}
/**
 * getCompName method returns the name of the company
 * @return returns Company Name
 */
public String getCompName() {
	return compName;
}
/**
 * setCompName sets the Company's name
 * @param compName stores the String that is being put into the company's name
 */
public void setCompName(String compName) {
	this.compName = compName;
}
/**
 * getPassword method returns the company's password
 * @return returns the Company's password
 */
public String getPassword() {
	return password;
}
/**
 * setPassword sets the Company's password
 * @param password stores the String that is being put into the company's password
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * getEmail method returns the company's email
 * @return returns the Company's email
 */
public String getEmail() {
	return email;
}
/**
 * setEmail sets the Company's email
 * @param email stores the String that is being put into the company's email
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * getCoupons method returns all the company's coupons
 * @return returns the all the Company's coupons
 */
public Collection<Coupon> getCoupons() {
	return coupons;
}
/**
 * setCoupons sets the Company's coupons
 * @param coupons stores the Collection<Coupon> that is being put into the company's coupons
 */
public void setCoupons(Collection<Coupon> coupons) {
	this.coupons = coupons;
}
@Override
public String toString() {
	return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email + "]";
}

}
