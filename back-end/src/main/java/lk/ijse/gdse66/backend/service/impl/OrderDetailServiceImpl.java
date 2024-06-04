package lk.ijse.gdse66.backend.service.impl;

import lk.ijse.gdse66.backend.dto.CustomDTO;
import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.OrderDTO;
import lk.ijse.gdse66.backend.dto.OrderDetailDTO;
import lk.ijse.gdse66.backend.entity.Customer;
import lk.ijse.gdse66.backend.entity.Order;
import lk.ijse.gdse66.backend.entity.OrderDetail;
import lk.ijse.gdse66.backend.entity.OrderDetailPK;
import lk.ijse.gdse66.backend.repositry.CustomerRepo;
import lk.ijse.gdse66.backend.repositry.ItemRepo;
import lk.ijse.gdse66.backend.repositry.OrderDetailRepo;
import lk.ijse.gdse66.backend.repositry.OrderRepo;
import lk.ijse.gdse66.backend.service.OrderDetailService;
import lk.ijse.gdse66.backend.service.exception.NotFoundException;
import lk.ijse.gdse66.backend.util.CustomerLoyaltyLevel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo inventoryRepo;

//    @Autowired
   private ModelMapper mapper;


    public OrderDetailServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
        mapper.addMappings(new PropertyMap<OrderDetail, OrderDetailDTO>() {
            @Override
            protected void configure() {
                map().setItem_code(source.getOrderDetailPK().getItem_code());
            }
        });
    }

    @Override
    public List<OrderDTO> getAllRefundOrders() {


//        List<OrderDTO> list = orderRepo.getAllRefundOrders().stream().map(order -> mapper.map(order, OrderDTO.class)).toList();

        List<Order> allRefundOrders = orderRepo.getAllRefundOrders();
        List<OrderDTO> orderDTOList = allRefundOrders.stream().map(order -> {
            OrderDTO orderDTO = mapper.map(order, OrderDTO.class);

            List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
            for (OrderDetail orderDetail : order.getOrderDetailDTOLis()) {
                String size = orderDetail.getOrderDetailPK().getSize();

                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(order.getOrderId());
                orderDetailDTO.setSize(size);
                orderDetailDTO.setItem_code(orderDetail.getItem_code().getItemCode());
                orderDetailDTO.setItemName(orderDetail.getItemName());
                orderDetailDTO.setUnitPrice(orderDetail.getUnitPrice());
                orderDetailDTO.setItemQty(orderDetail.getItemQty());

                orderDetailDTOList.add(orderDetailDTO);
            }

            orderDTO.setOrderDetailDTOList(orderDetailDTOList);
            return orderDTO;

        }).collect(Collectors.toList());


//        System.out.println(list);

        System.out.println("@@@@@@@@@@@@@@@@"+orderDTOList);
        return orderDTOList;
    }

    @Override
    public boolean refundOrder(String orderId) {
        if (orderRepo.existsById(orderId)) {
            System.out.println("Refund Order");

            // Fetch the order to be refunded
            Order refundOrder = orderRepo.findByOrderId(orderId);
            System.out.println(refundOrder.getCustomer_id().getCode());

            // Fetch the customer associated with the order
            Customer customer = customerRepo.findByCode(refundOrder.getCustomer_id().getCode());

            // Calculate the new loyalty points
            int currentPoints = customer.getLoyaltyPoints();
            int reducePoints = refundOrder.getAddedPoints();
            int newPoints = currentPoints - reducePoints;

            // Determine the new loyalty level based on the new points
            CustomerLoyaltyLevel loyaltyLevel = null;
            if (newPoints < 10) {
                loyaltyLevel = CustomerLoyaltyLevel.NEW;
            } else if (newPoints >= 10 && newPoints < 30) {
                loyaltyLevel = CustomerLoyaltyLevel.BRONZE;
            } else if (newPoints >= 30 && newPoints < 100) {
                loyaltyLevel = CustomerLoyaltyLevel.SILVER;
            } else if (newPoints >= 100) {
                loyaltyLevel = CustomerLoyaltyLevel.GOLD;
            }

            // Update the customer's loyalty level and points
            customer.setLoyaltyLevel(loyaltyLevel);
            customer.setLoyaltyPoints(newPoints);
            System.out.println("loyaltyLevel = " + customer.getLoyaltyLevel());
            System.out.println("loyaltyPoints = " + customer.getLoyaltyPoints());
            customerRepo.save(customer);

            // Adjust the inventory quantities based on the order details
            List<OrderDetail> orderDetailsByOrderId = orderDetailRepo.findOrderDetailsByOrderId(orderId);
            for (OrderDetail orderDetail : orderDetailsByOrderId) {
                String itemCode = orderDetail.getOrderDetailPK().getItem_code();
                String size = orderDetail.getOrderDetailPK().getSize();
                int availableQty = inventoryRepo.findQtyByItemCodeAndSize(itemCode, size);
                int newQty = availableQty + orderDetail.getItemQty();

                // Determine the new status based on the new quantity
                String status;
                if (newQty <= 0) {
                    status = "Not Available";
                } else if (newQty < 10) {
                    status = "Low";
                } else {
                    status = "Available";
                }

                // Update the inventory with the new quantity and status
                inventoryRepo.updateByItemCodeAndSize(newQty, status, itemCode, size);
            }

            // Delete the order from the repository
            orderRepo.deleteById(orderId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean refundOrderDetails(CustomDTO customDTO) {
        OrderDetailPK orderDetailPK = new OrderDetailPK(customDTO.getOrderId(), customDTO.getItemCode(), customDTO.getSize());
        if (orderDetailRepo.existsById(String.valueOf(orderDetailPK))) {
            /*Update order total //////////////////*/
            Order order = orderRepo.findByOrderId(customDTO.getOrderId());
            if (order != null) {
                double newTotalPrice = order.getTotalPrice() - customDTO.getUnitTotalPrice();
                order.setTotalPrice(newTotalPrice);
                orderRepo.save(order);
            }

            /*Update Item qty /////////////////////*/
            String itemCode = customDTO.getItemCode();
            String size = customDTO.getSize();
            int availableQty = inventoryRepo.findQtyByItemCodeAndSize(itemCode, size);
            int newQty = availableQty + customDTO.getQty();

            String status;
            if (newQty <= 0) {
                status = "Not Available";
            } else if (newQty < 10) {
                status = "Low";
            } else {
                status = "Available";
            }
            inventoryRepo.updateByItemCodeAndSize(newQty, status, itemCode, size);


            /*Delete order detail /////////////////*/
            orderDetailRepo.deleteById(String.valueOf(orderDetailPK));

            /*Delete order ////////////////////////*/
            if (customDTO.getArrayLength() == 0) {
                orderRepo.deleteById(customDTO.getOrderId());
                //System.out.println("Delete Order");
            }
            //System.out.println("exists");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public OrderDTO getOrderByOrderId(String orderId) {
        if (!orderRepo.existsById(orderId)){
            throw new NotFoundException("Order Id does not exists!");
        }
        Order order = orderRepo.findOrderByOrderId(orderId);
        return mapper.map(order, OrderDTO.class);
    }

    @Override
    public List<OrderDetailDTO> getOrderDetailListByOrderId(String orderId) {
        return null;
    }




}