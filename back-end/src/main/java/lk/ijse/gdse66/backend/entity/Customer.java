package lk.ijse.gdse66.backend.entity;

import jakarta.persistence.*;
import lk.ijse.gdse66.backend.util.CustomerLoyaltyLevel;
import lk.ijse.gdse66.backend.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id

    private String code;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String contact;
    private Date dob;
    private String address;
    private Date loyaltyDate;

    @Enumerated(EnumType.STRING)
    private CustomerLoyaltyLevel loyaltyLevel;

    private Integer loyaltyPoints;
    private Timestamp recentPurchaseDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();




}
