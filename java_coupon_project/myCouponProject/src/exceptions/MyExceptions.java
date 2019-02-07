package exceptions;
/**
 * The class MyExceptions is part of myCouponProject
 * The class MyExceptions deals with all the exceptions
 * @author Oshra & Shilo
 *
 */
public class MyExceptions{
	/**
	 * method Exceptions deals with the different exceptions 
	 * @param Exception e stores the automatic built in exceptions  
	 * @param String problem stores the name of the user which has the problem
	 */
	
	public static String Exceptions(Exception e, String problem) {
		String exceptions[] = e.getClass().toString().split(" ");
		String exception = exceptions[1];
		
		switch (exception){
			case ("DuplicateUserException"):
			
				return "sorry,"+problem+" is already in the system";

			case ("CustomerHasCouponAlready"):
				
				return  "sorry,"+problem+" already has this coupon";
				
			case ("NullPointerException"):
				
				return  "sorry,"+problem+" is not in the system";

			case("java.lang.SQLException"):
				
				System.out.println(e.getCause());
			return  "cannot connect with mysql";
				
			case ("java.lang.InterruptedException"):

				System.out.println(e.getCause());
			return  "the thread has been interrupted";

			case ("java.lang.ClassNotFoundException"):

				System.out.println(e.getCause());
			return  problem+" not found";

			case ("WrongDataInputException"):

				return "the data input cannot be proccesed";
					
			case ("DuplicateCouponTypeException"):
				return "this coupon already exists";
			
			case ("ParseException"):
				return  "problem with the date";
			
			case ("UnavailableCouponException"):
				return "this coupon ran out, sorry you can not purchase this coupon";
		}
		return exception;
	}	
				
		/**
		 * method Exceptions deals with only the built in exceptions 
		 * @param Exception e stores the automatic built in exceptions
		 */
		public static String Exceptions(Exception e) {
			String exceptions[] = e.getClass().toString().split(" ");
			String exception = exceptions[1];
			
			switch (exception){
			
			case ("UnavailableCouponException"):
				return "this coupon ran out, sorry you can not purchase this coupon";
		
			case ("CustomerHasCouponAlready"):
				return "sorry,this customer already has this coupon";
			
			case("java.lang.SQLException"):
				System.out.println(e.getCause());
			return "cannot connect with mysql";
				
			case ("java.lang.InterruptedException"):

				System.out.println(e.getCause());
			return "the thread has been interrupted";

			case ("WrongDataInputException"):

				return "the data input cannot be proccesed";
					
			case ("DuplicateCouponTypeException"):
				return "this coupon already exists";
			
			case ("ParseException"):
				return "problem with the date";
			
		case ("NullPointerException"):
			
			return  "Error, try again later";
		
		case ("java.lang.ClassNotFoundException"):

			System.out.println(e.getCause());
		return  "class not found";
			
		case ("DuplicateUserException"):
			
			return "sorry, he's already in the system";
			
		case ("java.lang.NullPointerException"):
			
			return  "Error, try again later";
			
			}
			
			return exception;
			
	}			
}
