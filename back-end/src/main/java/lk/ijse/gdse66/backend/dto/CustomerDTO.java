package lk.ijse.gdse66.backend.dto;

import lk.ijse.gdse66.backend.util.CustomerLoyaltyLevel;
import lk.ijse.gdse66.backend.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO {
    private String code;
    private String name;
    private String email;
    private Gender gender;
    private String contact;
    private Date dob;
    private String address;
    private Date loyaltyDate;
    private CustomerLoyaltyLevel loyaltyLevel;
    private Integer loyaltyPoints;
    private Timestamp recentPurchaseDate;



}
