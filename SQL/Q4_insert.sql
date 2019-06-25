delimiter //
create trigger insert_patient before insert
on Queue
for each row
begin
insert into Queue_Summary values (new.actual_time ,1 ,
  (select doctor_id from Appointment where new.appointment_id = Appointment.appointment_id))
ON DUPLICATE KEY UPDATE num_of_patients = num_of_patients+1 ;
end//
delimiter ;
