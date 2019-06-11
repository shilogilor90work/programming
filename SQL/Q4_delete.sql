delimiter //
create trigger delete_patient before delete
on Queue
for each row
begin
UPDATE Queue_Summary set num_of_patients = num_of_patients-1
 where Queue_Summary.`date` = date(old.actual_time) and
 doctor_id =  (select doctor_id from Appointment where old.appointment_id = Appointment.appointment_id);
end//
delimiter ;
