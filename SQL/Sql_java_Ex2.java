package masadei_netunim;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;



// the answer for questions 1-4 are at the bottom.
// we added a few more functions for comfort.

/**
 *
 * @authors shilo gilor and amiel liberman
 * t.z. 302537394  and  206328122
 *
 */
public class Sql_queue {
	/**
	 *
	 * @param queue is the queue to run in the mysql database
	 * @return the string of the result of the queue
	 * @throws Exception
	 */
	public String run_queue(String queue) throws Exception
	{
		String result = "";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/M_N?useSSL=false","root" , "your_password");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(queue);
		int numOfColumns = rs.getMetaData().getColumnCount();
		while (rs.next()){
			for (int col = 1; col <= numOfColumns; col++)
			{
				result= result + rs.getString(col) + " ";
			}
			result = result + "\n";
		}
		stmt.close();
		con.close();
		return result;
	}
	/**
	 *
	 * @param queue is the queue to run in the mysql database
	 * @throws Exception
	 */
	public void run_queue_update(String queue) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/M_N?useSSL=false","root" , "your_password");
		Statement stmt = con.createStatement();
		stmt.executeUpdate(queue);
		stmt.close();
		con.close();
	}
	/**
	 *
	 * @param doctor_id is the doctor id
	 * @param doctor_type is the doctor type
	 * @param doctor_name is the doctor name
	 * @param salary is the doctor salary
	 */
	public void insert_doctor(String doctor_id , String doctor_type , String doctor_name , double salary)
	{
		String queue = "insert into Doctors values(\""+doctor_id +"\",\"" + doctor_type + "\" , \"" + doctor_name +"\" ,"+ salary +")";
		try {
			run_queue_update(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *
	 * @param patient_id is the patient id
	 * @param patient_name is the patient name
	 */
	public void insert_patient(String patient_id , String patient_name)
	{
		String queue = "insert into Patients values(\""+patient_id +"\",\"" + patient_name + "\")";
		try {
			run_queue_update(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *
	 * @param patient_id is the patient id
	 * @param doctor_id is the doctor id
	 * @param rs is the result of the queue
	 */
	public void insert_appointment(String patient_id , String doctor_id , Date rs)
	{
		Timestamp dateDB = new java.sql.Timestamp(rs.getTime());
		String queue = "insert into Appointment (`patient_id` , `doctor_id` , `appointment_time`)values(\""+patient_id +"\",\"" + doctor_id + "\",\"" + dateDB + "\")";
		try {
			run_queue_update(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *
	 * @param appointment_id is the appointment id
	 * @throws ParseException
	 */
	public void insert_queue(int appointment_id) throws ParseException
	{
		String queue = "insert into Queue values("+appointment_id +" ,  CURRENT_TIMESTAMP )";
		try {
			run_queue_update(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *
	 * @param date is the date of appointments
	 * @param doctor_id is the doctor id
	 * @param num_of_patients is the number of patients for that date
	 */
	public void insert_queue_summary(Date date , String doctor_id ,int num_of_patients)
	{
		String queue = "insert into Queue_Summary values(" + date + " , \"" + doctor_id + "\" , " + num_of_patients + ")";
		try {
			run_queue_update(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Q1 we are pulling patient_id and patient_name and appointment_time , where the doctor id is the id given ordered by the appointment time
	 * @param doctor_id the doctor id
	 * @return the queue result
	 */
	public String q1(String doctor_id)
	{
		String rs = null;
		String queue = "select Patients.patient_id , Patients.patient_name , appointment_time " +
				"from Appointment join Patients on " +
				"Appointment.patient_id = Patients.patient_id " +
				"where doctor_id = "+ doctor_id + " " +
				"ORDER BY appointment_time";
		try {
			rs = run_queue(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * Q2 this is the create function of the procedure, there is no need to run this more than once
	 * this procedure inserts into Queue the patient id and the current time
	 */
	public void q2_create_procedure()
	{
		String queue = "delimiter // CREATE DEFINER=`root`@`localhost` PROCEDURE `M_N.spqueue_patient_in`(in patient_id varchar(9)) BEGIN insert into Queue values((select appointment_id from Appointment where M_N.Appointment.patient_id=patient_id limit 1), CURRENT_TIMESTAMP); END// delimiter ;";
		try {
			run_queue_update(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Q2 this is how we call the procedure after created
	 * @param patient_id is the patient id
	 */
	public void q2(String patient_id)
	{
		String queue = "call M_N.spqueue_patient_in";
		try {
			run_queue_update(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Q3 this is the create function for the view
	 * this view shows the top 10 patients that waited the most
	 */
	public void q3_create_view()
	{
		String queue = "CREATE \n" +
				"    ALGORITHM = UNDEFINED \n" +
				"    DEFINER = `root`@`localhost` \n" +
				"    SQL SECURITY DEFINER\n" +
				"VIEW `new_view` AS\n" +
				"    SELECT \n" +
				"        `patients`.`patient_id` AS `patient_id`,\n" +
				"        `patients`.`patient_name` AS `patient_name`,\n" +
				"        (`queue`.`actual_time` - `appointment`.`appointment_time`) AS `lateness_d_hh_mm_ss`\n" +
				"    FROM\n" +
				"        ((`patients`\n" +
				"        JOIN `appointment` ON ((`patients`.`patient_id` = `appointment`.`patient_id`)))\n" +
				"        JOIN `queue` ON ((`appointment`.`appointment_id` = `queue`.`appointment_id`)))\n" +
				"    ORDER BY `lateness_d_hh_mm_ss` DESC\n" +
				"    LIMIT 10";
		try {
			run_queue_update(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Q3 this is the run function for the view
	 * @return the string of the result of the view
	 */
	public String q3()
	{
		String rs = null;
		String queue = "SELECT * FROM M_N.new_view;";
		try {
			rs = run_queue(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * Q4 this is the create trigger function for insert
	 */
	public void q4_create_trigger_insert()
	{
		String queue = "delimiter //\n" +
				"create trigger insert_patient before insert\n" +
				"on Queue\n" +
				"for each row\n" +
				"begin\n" +
				"insert into Queue_Summary values (new.actual_time ,1 ,(select doctor_id from Appointment where new.appointment_id = Appointment.appointment_id))\n" +
				"ON DUPLICATE KEY UPDATE num_of_patients = num_of_patients+1 ;\n" +
				"end//\n" +
				"delimiter ;";
		try {
			run_queue_update(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Q4 this is the create trigger for delete
	 */
	public void q4_create_trigger_delete()
	{
		String queue = "delimiter //\n" +
				"create trigger delete_patient before delete\n" +
				"on Queue\n" +
				"for each row\n" +
				"begin\n" +
				"UPDATE Queue_Summary set num_of_patients = num_of_patients-1 \n" +
				" where Queue_Summary.`date` = date(old.actual_time) and \n" +
				" doctor_id =  (select doctor_id from Appointment where old.appointment_id = Appointment.appointment_id);\n" +
				"end//\n" +
				"delimiter ;";
		try {
			run_queue_update(queue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
