package lk.ijse.gdse66.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lk.ijse.gdse66.backend.util.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.sampled.Line;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suplier {

    @Id
    private String supplierCode ;
    private String supplierName;
    @Enumerated(EnumType.STRING)
    private  Category category ;
    private String address;
    private String mobileNumber ;
    private String landlineNumber ;
    private String email;
}
