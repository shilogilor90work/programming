delimiter //
CREATE DEFINER=`root`@`localhost`
PROCEDURE `M_N.spqueue_patient_in`(in patient_id varchar(9))
 BEGIN
 insert into Queue
 values(
 (select appointment_id from Appointment where M_N.Appointment.patient_id=patient_id limit 1),
 CURRENT_TIMESTAMP); END//
 delimiter ;


 #call M_N.spqueue_patient_in; // run procedure
