package lk.ijse.gdse66.backend.dto;

import lk.ijse.gdse66.backend.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostSoldItemDTO {
    private Item item;
    private Long totalQty;
}
