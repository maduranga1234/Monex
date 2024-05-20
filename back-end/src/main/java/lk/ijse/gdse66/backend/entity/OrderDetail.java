package lk.ijse.gdse66.backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailPK orderDetailPK;

    private String itemName;
    private Double unitPrice;
    private Integer itemQty;

    @ManyToOne
    @JoinColumn(name = "order_id",
            referencedColumnName = "order_id",insertable = false,
            updatable = false)
    private Order order_id;

    @ManyToOne
    @JoinColumn(name = "item_code",
            referencedColumnName = "itemCode",
            insertable = false,
            updatable = false)
    private Item item_code;
}
