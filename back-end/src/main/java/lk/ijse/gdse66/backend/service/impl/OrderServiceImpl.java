package lk.ijse.gdse66.backend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.ItemDTO;
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
import lk.ijse.gdse66.backend.service.OrderService;
import lk.ijse.gdse66.backend.util.CustomerLoyaltyLevel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ItemRepo inventoryRepo;
    @Autowired
    private ModelMapper mapper;


    @Override
    public void placeOrder(OrderDTO orderDTO) {
        Order order = mapper.map(orderDTO, Order.class);


        Customer customer = customerRepo.findByCode(orderDTO.getCustomer_id());
        order.setCustomer_id(customer);

        int currentPoints = customer.getLoyaltyPoints();
        int addedPoints = orderDTO.getAddedPoints();

        int newPoints = currentPoints+addedPoints;
        CustomerLoyaltyLevel loyaltyLevel = null;
        if (newPoints < 10){
            loyaltyLevel = CustomerLoyaltyLevel.NEW;
        }else if (newPoints >= 10 && newPoints<30){
            loyaltyLevel = CustomerLoyaltyLevel.BRONZE;
        } else if (newPoints >= 30 && newPoints<100) {
            loyaltyLevel = CustomerLoyaltyLevel.SILVER;
        } else if (newPoints >= 100) {
            loyaltyLevel = CustomerLoyaltyLevel.GOLD;
        }
        customer.setLoyaltyLevel(loyaltyLevel);
        customer.setLoyaltyPoints(newPoints);

        System.out.println("order Date  ="+ orderDTO.getOrderDate());
        customer.setRecentPurchaseDate(orderDTO.getOrderDate());
        customerRepo.save(customer);

        /*update item and save order-details ////////////////////////////////*/

        System.out.println("qqqqqqqqqqqqqqqqqq"+ order);
        orderRepo.save(order);
        System.out.println("12345"+orderDTO.getOrderDetailDTOList());
        for (OrderDetailDTO detailDTO : orderDTO.getOrderDetailDTOList()) {
            OrderDetailPK orderDetailPK = new OrderDetailPK(detailDTO.getOrder_id(),detailDTO.getItem_code(),detailDTO.getSize());

            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setOrderDetailPK(orderDetailPK);
            orderDetail.setItemName(detailDTO.getItemName());
            orderDetail.setUnitPrice(detailDTO.getUnitPrice());
            orderDetail.setItemQty(detailDTO.getItemQty());



            orderDetailRepo.save(orderDetail);

            /*update item ////////////////////////////////*/
            int availableQty = inventoryRepo.findQtyByItemCodeAndSize(detailDTO.getItem_code(), detailDTO.getSize());
            int newQty = availableQty - detailDTO.getItemQty();

            String status;
            if (newQty<=0){
                status="Not Available";
            } else if (newQty<10) {
                status="Low";
            } else {
                status="Available";
            }
            inventoryRepo.updateByItemCodeAndSize(newQty, status, detailDTO.getItem_code(),detailDTO.getSize());
        }
    }


    @Override
    public ItemDTO searchItemByCode(String code) {
        return null;
    }

    @Override
    public List<String> getAllItemCodes() {
        return null;
    }

    @Override
    public CustomerDTO searchCustomerById(String code) {
        return null;
    }

    @Override
    public List<String> getAllCustomerIds() {
        return null;
    }

    @Override
    public String generateNextOrderId() {
        String prefix = "ORD-";
        String id = "";

        Order lastOrder = orderRepo.findTopByOrderByOrderIdDesc();
        int nextNumericPart;
        if (lastOrder != null) {
            String lastCode = lastOrder.getOrderId();
            String numericPartString = lastCode.substring(prefix.length());
            try {
                int numericPart = Integer.parseInt(numericPartString);
                nextNumericPart = numericPart + 1;
            } catch (NumberFormatException e) {
                nextNumericPart = 1;
            }
        } else {
            nextNumericPart = 1;
        }
        id = prefix + String.format("%04d", nextNumericPart);

        System.out.println("Order next id ="+id);
        return id;
    }
}
