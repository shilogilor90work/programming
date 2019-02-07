package members;


import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import utilities.CouponType;
/**
 * The class Coupon is part of myCouponProject
 * the class Coupon contains the basic description of a cupon
 * @author Oshra & Shilo
 *
 */
@XmlRootElement
public class Coupon implements Serializable{
private long id;
private String title;
private Date startDate;
private Date endDate;
private int amount;
private CouponType type;
private String message;
private double price;
private String image;
/**
 *  this constructor creates a new Coupon
 * @param long id stores the coupon's id
 * @param String title stores the coupon's title
 * @param Date startDate stores the coupon's start date
 * @param Date endDate stores the coupon's end date
 * @param int amount stores the amount of coupon's
 * @param CouponType type stores the coupon's type
 * @param String message stores the coupon's description
 * @param double price stores the coupon's price
 * @param String image stores the coupon's image
 */
public Coupon(long id, String title, Date startDate, Date endDate, int amount, CouponType type, String message,
		double price, String image) {
	super();
	this.id = id;
	this.title = title;
	this.startDate = startDate;
	this.endDate = endDate;
	this.amount = amount;
	this.type = type;
	this.message = message;
	this.price = price;
	this.image = image;
}
public Coupon(){}
/**
 * getId method returns the coupon's ID
 * @return long returns the coupon's ID 
 */
public long getId() {
	return id;
}
/**
 * setId method adds content into the coupon's ID
 * @param id stores the long that is being put into the coupon's ID
 */
public void setId(long id) {
	this.id = id;
}
/**
 * getTitle method returns the title of the coupon
 * @return String returns Coupon title
 */
public String getTitle() {
	return title;
}
/**
 * setTitle sets the Coupon's title
 * @param String title stores the String that is being put into the coupon's title
 */
public void setTitle(String title) {
	this.title = title;
}
/**
 * getStartDate method returns the start date of the coupon
 * @return Date returns the coupon's start date
 */
public Date getStartDate() {
	return startDate;
}
/**
 * setStartDate sets the Coupon's start date
 * @param Date startDate stores the Date that is being put into the coupon's start date
 */
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
/**
 * getEndDate method returns the end date of the coupon
 * @return Date returns the coupon's end date
 */
public Date getEndDate() {
	return endDate;
}
/**
 * setEndDate sets the Coupon's end date
 * @param Date endDate stores the Date that is being put into the coupon's end date
 */
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
/**
 * getAmount method returns the amount of coupon's
 * @return int returns the amount of coupon's 
 */
public int getAmount() {
	return amount;
}
/**
 * setAmount sets the amount of Coupon's 
 * @param int amount stores the int amount of coupon's
 */
public void setAmount(int amount) {
	this.amount = amount;
}
/**
 * getType method returns the type of the coupon
 * @return CouponType returns the coupon's type
 * @see CouponType
 */
public CouponType getType() {
	return type;
}
/**
 * setType sets the Coupon's type
 * @param CouponType type stores the CouponType that is being put into the coupon's type
 * @see CouponType
 */
public void setType(CouponType type) {
	this.type = type;
}
/**
 * getMessage method returns the coupon's message
 * @return String returns the coupon's message
 */
public String getMessage() {
	return message;
}
/**
 * setMessage sets the Coupon's message
 * @param String message stores the String that is being put into the coupon's message
 */
public void setMessage(String message) {
	this.message = message;
}
/**
 * getPrice method returns the coupon's price
 * @return double returns the coupon's price
 */
public double getPrice() {
	return price;
}
/**
 * setPrice sets the Coupon's price
 * @param double price stores the double that is being put into the coupon's price
 */
public void setPrice(double price) {
	this.price = price;
}
/**
 * getImage method returns the coupon's image
 * @return String returns the coupon's image
 */
public String getImage() {
	return image;
}
/**
 * setImage sets the Coupon's image
 * @param String image stores the String that is being put into the coupon's image
 */
public void setImage(String image) {
	this.image = image;
}
@Override
public String toString() {
	return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
			+ amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image=" + image + "]";
}

}
