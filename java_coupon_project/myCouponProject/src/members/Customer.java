package members;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class Customer is part of myCouponProject
 * the class Customer contains the basic description of a customer
 * @author Oshra & Shilo
 *
 */
@XmlRootElement
public class Customer implements Serializable {
private long id;
private String custName;
private String password;
/**
 * * this constructor creates a new Company that has coupons
 * @param long id stores the company's ID
 * @param String custName stores the customer's name
 * @param String password stores the customer's password
 */
public Customer(long id, String custName, String password) {
	super();
	this.id = id;
	this.custName = custName;
	this.password = password;
}
public Customer(){}
/**
 * getId method returns the customer's ID
 * @return long returns the customer's ID 
 */
public long getId() {
	return id;
}
/**
 * setId method adds content into the customer's ID
 * @param id stores the long that is being put into the customer's ID
 */
public void setId(long id) {
	this.id = id;
}
/**
 * getCustName method returns the customer's name
 * @return String returns customer's name
 */
public String getCustName() {
	return custName;
}
/**
 * setCustName sets the customer's name
 * @param String custName stores the customer's name
 */
public void setCustName(String custName) {
	this.custName = custName;
}
/**
 * getPassword method returns the customer's password
 * @return String returns customer's password
 */
public String getPassword() {
	return password;
}
/**
 * setPassword sets the customer's password
 * @param String password stores the customer's password
 */
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "Customer [id=" + id + ", custName=" + custName + ", password=" + password +  "]";
}

}
