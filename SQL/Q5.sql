select doctor_name, salary
from Doctors
inner join Queue_summary on Doctors.doctor_id = Queue_Summary.doctor_id
where Queue_Summary.num_of_patients >= 5 and Queue_summary.`date` = "2019-03-19";
