package lk.ijse.gdse66.backend.dto;

import lk.ijse.gdse66.backend.util.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SuplierDTO {
    private String supplierCode ;
    private String supplierName;
    private Category category ;
    private String address;
    private String mobileNumber ;
    private String landlineNumber ;
    private String email;
}
