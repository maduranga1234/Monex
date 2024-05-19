package lk.ijse.gdse66.backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class OrderDetail {

    @EmbeddedId
    private OrderDetailPK orderDetailPK;
    private String itemName;
    private Double unitPrice;
    private Integer itemQty;
    @ManyToOne
    @JoinColumn(name = "orderId",
            referencedColumnName = "orderId",insertable = false,
            updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "itemCode",
            referencedColumnName = "itemCode",
            insertable = false,
            updatable = false)
    private Item item;

}
