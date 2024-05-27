package lk.ijse.gdse66.backend.service;

import lk.ijse.gdse66.backend.dto.CustomDTO;
import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.OrderDTO;
import lk.ijse.gdse66.backend.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {

    List<OrderDTO> getAllRefundOrders();
    boolean refundOrder(String orderId);

    boolean refundOrderDetails(CustomDTO customDTO);
    OrderDTO getOrderByOrderId(String orderId);
    List<OrderDetailDTO> getOrderDetailListByOrderId(String orderId);
}
