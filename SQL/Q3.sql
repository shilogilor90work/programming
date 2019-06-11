 CREATE  
        ALGORITHM = UNDEFINED
        DEFINER = `root`@`localhost`
        SQL SECURITY DEFINER
    VIEW `new_view` AS
        SELECT
            `patients`.`patient_id` AS `patient_id`,
            `patients`.`patient_name` AS `patient_name`,
            (`queue`.`actual_time` - `appointment`.`appointment_time`) AS `lateness_d_hh_mm_ss`
        FROM
            ((`patients`
            JOIN `appointment` ON ((`patients`.`patient_id` = `appointment`.`patient_id`)))
            JOIN `queue` ON ((`appointment`.`appointment_id` = `queue`.`appointment_id`)))
        ORDER BY `lateness_d_hh_mm_ss` DESC
        LIMIT 10;


        #SELECT * FROM M_N.new_view; // run view
