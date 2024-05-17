package lk.ijse.gdse66.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private String  itemCode;
    private String  itemDesc;
    private String itemPicture;
    private String Category;
    private Integer size6;
    private Integer size8;
    private Integer size10;
    private Integer size11;
    private String supplierCode;
    private String supplierName;
    private Double unitPriceSale;
    private Double unitPriceBuy;
    private Double expectedProfit;
    private Double profitMargin ;
    private String status ;
}
