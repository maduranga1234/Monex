package lk.ijse.gdse66.backend.service;

import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.ItemDTO;
import lk.ijse.gdse66.backend.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    void placeOrder(OrderDTO orderDTO);
    ItemDTO searchItemByCode(String code);
    List<String> getAllItemCodes();
    CustomerDTO searchCustomerById(String code);
    List<String> getAllCustomerIds();
    String generateNextOrderId();


}
