package lk.ijse.gdse66.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lk.ijse.gdse66.backend.util.Gender;
import lk.ijse.gdse66.backend.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private String employeeCode;
    private String employeeName;
    private String  employeeProfilePic;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String status;
    private String Designation;
    @Enumerated(EnumType.STRING)
    private Role accessRole;
    private Date dob;
    private Date dateOfJoinDate;
    private  String attachedbranch;
    private String addressLine01;
    private String addressLine02 ;
    private String  addressLine03;
    private String  addressLine04;
    private String  addressLine05;
    private String contactNo;
    private String emailToContact;
    private String informInCaseOfEmergency;
    private String emergencyContact;
}
