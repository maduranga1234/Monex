package lk.ijse.gdse66.backend.service;

import lk.ijse.gdse66.backend.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO saveSale(OrderDTO orderDTO);

    boolean deleteSale(String id);
    String generateNextId();
    List<OrderDTO> searchSale(String id);


}
