package main;




import java.text.SimpleDateFormat;
import java.util.Date;

import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;
import members.Company;
import members.Coupon;
import backgroundFunctions.CouponSystem;
import utilities.CouponType;
import members.Customer;
/**
 * The class TestMain is part of myCouponProject
 * The class TestMain is a main class testing the entire project
 * @author Oshra & Shilo
 *
 */
public class TestMain {

	public static void main(String[] args) throws Exception {
       
		CustomerFacade ccfcustomershilo=null;
		ccfcustomershilo =(CustomerFacade) CouponSystem.getInstance().login("shilo", "4321", "customer");
	
		System.out.println(ccfcustomershilo.getAllCoupons());
		
		
		/*
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

		String date_s = "2011-04-10";
        Date startDate = dt.parse(date_s);
        
        
        String date_s1 = "2018-01-18";
        Date date1 = dt.parse(date_s1);
        String date_s2 = "2017-04-18";
        Date date2 = dt.parse(date_s2);
        String date_s3 = "2020-01-23";
        Date date3 = dt.parse(date_s3);
        String date_s4 = "2018-01-18";
        Date date4 = dt.parse(date_s4);
        String date_s5 = "2019-01-13";
        Date date5 = dt.parse(date_s5);
        String date_s6 = "2017-07-18";
        Date date6 = dt.parse(date_s6);
		
		
		Coupon coupon = new Coupon(0, "osemcoup", startDate ,date1, 3, CouponType.FOOD, "veryfknprc", 4562, "aue bafxad");
		Coupon coupon1 = new Coupon(0, "osemmycoup", startDate, date2, 1, CouponType.ELECTRICTY, "verypricey", 4564.2, "askjoa");
		Coupon coupon2 = new Coupon(0, "teva", startDate, date6, 1, CouponType.HEALTH, "health", 44.2, "asdvajva");
		Coupon coupon3 = new Coupon(0, "vet", startDate, date4, 2, CouponType.TRAVELING, "deffrent", 98, "hi");
		Coupon coupon4 = new Coupon(0, "lila", startDate,date3, 3, CouponType.SPROTS, "hard", 123, "never");
		Coupon coupon5 = new Coupon(0, "lilasheli", startDate, date5, 1, CouponType.ELECTRICTY, "verypricey", 4564.2, "dsaja");
		Coupon coupon6 = new Coupon(0, "vetsheli", startDate, date6, 3, CouponType.ELECTRICTY, "fdsfds", 8743.12, "acnsau");
		Coupon coupon7 = new Coupon(0, "tevasheli", startDate, date4, 3, CouponType.SPROTS, "awtyydfv", 452, "shyvbdha");

		Customer customermoshe = new Customer(265, "moshe", "1");
		Customer customerdavid = new Customer(265, "david", "432");
		Customer customershilo= new Customer(265, "shilo", "4321");
		Customer customermayyan = new Customer(265, "maayan", "maayan");
		Customer customeridan = new Customer(265, "idan", "tov");
		Customer customeryoav = new Customer(265, "yoav", "mazal");
		Customer customeryael = new Customer(265, "yael", "hi");
		Customer customeroshra = new Customer(265, "oshra", "fat");
		Customer customerdvir = new Customer(265, "dvir", "dip");
		Customer customershama = new Customer(265, "shama", "1");
		Customer customersheva = new Customer(265, "sheva", "loaf");
		Customer customermotti = new Customer(265, "motti", "lone");
		Customer customeromer = new Customer(265, "omer", "hi");
		Customer customeradam = new Customer(265, "adam", "tim");
		Customer customeradamupdated = new Customer(265, "adam", "2");
		
		Company companylila = new Company(0, "lila", "mypassword", "hello@yahoo.com");
		Company companyvet = new Company(0, "vet", "mypass", "hola@yahoo.com");
		Company companyteva = new Company(0, "teva", "mypasing", "hola@yahoo.com");
		Company companyosem = new Company(0, "osem", "mine", "hola@yahoo.com");
		Company companyupdated = new Company(0, "osem", "my", "hola@yahoo.com");

		AdminFacade ccf = null;
		ccf=(AdminFacade) CouponSystem.getInstance().login("admin", "password", "admin");
		ccf.createCustomer(customermoshe);
		ccf.createCustomer(customerdavid);
		ccf.createCustomer(customershilo);
		ccf.createCustomer(customermayyan);
		ccf.createCustomer(customeridan);
		ccf.createCustomer(customeryoav);
		ccf.createCustomer(customeryael);
		ccf.createCustomer(customeroshra);
		ccf.createCustomer(customerdvir);
		ccf.createCustomer(customershama);
		ccf.createCustomer(customersheva);
		ccf.createCustomer(customermotti);
		ccf.createCustomer(customeromer);
		ccf.createCustomer(customeradam);
		ccf.createCustomer(customermoshe);

			
		ccf.createCompany(companyvet);
		ccf.createCompany(companyteva);
		ccf.createCompany(companylila);
		ccf.createCompany(companyosem);


		System.out.println(ccf.getAllCustomers());
		System.out.println(ccf.getAllCompanies());
		
		
		
		
		CompanyFacade ccfcompanylila = null;
		ccfcompanylila= (CompanyFacade) CouponSystem.getInstance().login("lila", "mypassword", "company");
		
		ccfcompanylila.createCoupon(coupon4);
		ccfcompanylila.createCoupon(coupon5);
		
		
		System.out.println(ccfcompanylila.getAllCoupons());
		
		CompanyFacade ccfcompanyvet = null;
		ccfcompanyvet= (CompanyFacade) CouponSystem.getInstance().login("vet", "mypass", "company");
		
		ccfcompanyvet.createCoupon(coupon3);
		ccfcompanyvet.createCoupon(coupon6);
		
		CompanyFacade ccfcompanyosem = null;
		ccfcompanyosem= (CompanyFacade) CouponSystem.getInstance().login("osem", "mine", "company");

		ccfcompanyosem.createCoupon(coupon);
		ccfcompanyosem.createCoupon(coupon1);
		System.out.println(ccfcompanyosem.printCompany());
		System.out.println(ccfcompanyosem.getAllCoupons());

		CompanyFacade ccfcompanyteva = null;
		ccfcompanyteva= (CompanyFacade) CouponSystem.getInstance().login("teva", "mypasing", "company");
//	this should not work duplicate coupon
		ccfcompanyteva.createCoupon(coupon7);
		ccfcompanyteva.createCoupon(coupon2);
		ccfcompanyteva.createCoupon(coupon1);


		
		
		//now customers
		
		CustomerFacade ccfcustomermoshe=null;
		ccfcustomermoshe =(CustomerFacade) CouponSystem.getInstance().login("moshe", "1", "customer");

		ccfcustomermoshe.purchaseCoupon(coupon3);
		ccfcustomermoshe.purchaseCoupon(coupon2);
		ccfcustomermoshe.purchaseCoupon(coupon6);
			
		System.out.println(ccfcustomermoshe.getAllPurchasedCoupons());
		
		
		CustomerFacade ccfcustomerdavid=null;
		ccfcustomerdavid =(CustomerFacade) CouponSystem.getInstance().login("david", "432", "customer");
		
		ccfcustomerdavid.purchaseCoupon(coupon2);
		ccfcustomerdavid.purchaseCoupon(coupon6);
		ccfcustomerdavid.purchaseCoupon(coupon1);
		System.out.println(ccfcustomerdavid.getAllPurchasedCoupons());

		CustomerFacade ccfcustomershilo=null;
	ccfcustomershilo =(CustomerFacade) CouponSystem.getInstance().login("shilo", "4321", "customer");
		
	ccfcustomershilo.purchaseCoupon(coupon1);
	ccfcustomershilo.purchaseCoupon(coupon5);
	ccfcustomershilo.purchaseCoupon(coupon2);
		System.out.println(ccfcustomershilo.getAllPurchasedCouponsByPrice(300.6));
		
		CustomerFacade ccfcustomermaayan=null;
		ccfcustomermaayan =(CustomerFacade) CouponSystem.getInstance().login("maayan", "maayan", "customer");
			
		ccfcustomermaayan.purchaseCoupon(coupon1);
		ccfcustomermaayan.purchaseCoupon(coupon5);
		ccfcustomermaayan.purchaseCoupon(coupon2);
			System.out.println(ccfcustomermaayan.getAllPurchasedCouponsByType(CouponType.HEALTH));
		
			
			ccf.updateCompany(companyupdated);
			ccf.updateCustomer(customeradamupdated);
			
		ccfcompanyteva.removeCoupon(coupon7);	
		
		ccf.removeCustomer(customeryael);
		ccf.removeCustomer(customermoshe);
		ccf.removeCompany(companylila);
			
	

			*/
	}

}
