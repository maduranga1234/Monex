package lk.ijse.gdse66.backend.entity;

import jakarta.persistence.*;
import jdk.jfr.Percentage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    private String itemCode;
    private String itemDesc;
    @Column(columnDefinition = "LONGTEXT")
    private String itemPicture;
    private String category;
    private Integer size6;
    private Integer size8;
    private Integer size10;
    private Integer size11;
    private String supplierCode;
    private String supplierName;
    private Double unitPriceSale;
    private Double unitPriceBuy;
    private Double expectedProfit;
    private Double profitMargin;
    private String status;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item_code")
    private List<OrderDetail> orderDetails = new ArrayList<>();



}