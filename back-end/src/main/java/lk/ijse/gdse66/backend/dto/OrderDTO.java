package lk.ijse.gdse66.backend.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lk.ijse.gdse66.backend.entity.Customer;
import lk.ijse.gdse66.backend.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private String orderId;
    private Timestamp orderDate;
    private Double totalPrice;
    private Integer addedPoints;
    private String cashierName;
    private String paymentMethod;


    private Customer customer;

    private String customerName;
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
