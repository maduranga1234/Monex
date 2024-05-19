package lk.ijse.gdse66.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
public class Order {

  @Id
  private String orderId;
  private Timestamp orderDate;
  private Double totalPrice;
  private Integer addedPoints;
  private String cashierName;
  private String paymentMethod;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", referencedColumnName = "code")
  private Customer customer;
  private String customerName;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "order")
  private List<OrderDetail> orderDetails = new ArrayList<>();



}
