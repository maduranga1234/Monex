package lk.ijse.gdse66.backend.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.ijse.gdse66.backend.util.Gender;
import lk.ijse.gdse66.backend.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
    private String employeeCode;
    private String employeeName;
    private String  employeeProfilePic;
    private Gender gender;
    private String status;
    private String designation;
    private Role accessRole;
    private Date dob;
    private Date dateOfJoinDate;
    private String attachedbranch;
    private String address;
    private String contactNo;
    private String emailToContact;
    private String informInCaseOfEmergency;
    private String emergencyContact;
}
