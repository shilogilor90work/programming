package utilities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * The class DateToStringAndVs is part of myCouponProject
 * The class DateToStringAndVs translates a string into a date and a date into a string
 * @author Oshra & Shilo
 *
 */

public class DateToStringAndVs {
	/**
	 * the method stringToDate translates a string into a date format
	 * @param String date stores the string that is being translated into a date format
	 * @return returns a Date in a Date format
	 * @throws ParseException
	 */
	public static Date stringToDate(String date) throws ParseException
	{
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = dt.parse(date);
        return newDate;
	}
	/**
	 * the method dateToString translates a date into String format
	 * @param Date date stores the date that is being translated into String format
	 * @return returns a Date in String format
	 */
	public static String dateToString(Date date) 
	{
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String  newDate = dt.format(date);
        return newDate;
	}
	

}
