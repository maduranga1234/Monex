package lk.ijse.gdse66.backend.service.impl;

import lk.ijse.gdse66.backend.dto.OrderDTO;
import lk.ijse.gdse66.backend.entity.Order;
import lk.ijse.gdse66.backend.repositry.OrderRepo;
import lk.ijse.gdse66.backend.service.OrderService;
import lk.ijse.gdse66.backend.service.exception.DuplicateRecordException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper mapper;

    public OrderDTO saveSale(OrderDTO orderDTO) {
        if (orderRepo.existsById(orderDTO.getOrderId())) {
            throw new DuplicateRecordException("Order ID already exists!");
        }
        Order order = mapper.map(orderDTO, Order.class);
        Order savedOrder = orderRepo.save(order);
        return mapper.map(savedOrder, OrderDTO.class);
    }

    @Override
    public boolean deleteSale(String id) {
        return false;
    }

    @Override
    public String generateNextId() {
        return null;
    }

    @Override
    public List<OrderDTO> searchSale(String id) {
        return null;
    }
}
