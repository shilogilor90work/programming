package dbdao;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import exceptions.DuplicateCouponTypeException;
import exceptions.WrongDataInputException;
import members.Coupon;
/**
 * The interface CouponDAO is part of myCouponProject
 * @author Oshra & Shilo
 *
 */
public interface CouponDAO {
		public void createCoupon (Coupon coupon) throws SQLException, DuplicateCouponTypeException, ClassNotFoundException, InterruptedException ;
		public void removeCoupon (Coupon coupon) throws SQLException, ClassNotFoundException, InterruptedException, ParseException;
		public void updateCoupon (Coupon coupon) throws SQLException, WrongDataInputException, ClassNotFoundException, InterruptedException;
		public Coupon getCoupon (long id) throws SQLException, ClassNotFoundException, InterruptedException, ParseException ;
		public Collection <Coupon> getAllCoupons() throws SQLException, ClassNotFoundException, InterruptedException, ParseException ;
		public Collection <Coupon> getCouponByType (String couponType) throws SQLException, ClassNotFoundException, InterruptedException, ParseException;
}
