package lk.ijse.gdse66.backend.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetailPK implements Serializable {

    private String orderId;
    private String itemCode;
    private String size;
}
