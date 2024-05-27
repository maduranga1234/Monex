package lk.ijse.gdse66.backend.controller;

import lk.ijse.gdse66.backend.dto.CustomDTO;
import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.OrderDTO;
import lk.ijse.gdse66.backend.dto.OrderDetailDTO;
import lk.ijse.gdse66.backend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orderDetail")
@CrossOrigin(origins = "*")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;




    @DeleteMapping("/refundOrder")
    public ResponseEntity<String> refundOrder(@RequestParam String orderId) {
        System.out.println("refundOrder = "+orderId);
        try {
            boolean isRefunded = orderDetailService.refundOrder(orderId);
            if (isRefunded) {
                return ResponseEntity.ok("Order refunded successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while refunding the order");
        }
    }



    @DeleteMapping("/refundOrderDetail")
    public ResponseEntity<String> refundOrderDetail(@RequestBody CustomDTO customDTO){
        System.out.println(customDTO);
        try {
            boolean isRefunded = orderDetailService.refundOrderDetails(customDTO);
            if (isRefunded) {
                return ResponseEntity.ok("Order Item refunded successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Item not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while refunding the order item");
        }
    }

    @GetMapping("/getAllRefundOrders")
    public List<OrderDTO> getAllRefundOrders(){

        System.out.println(orderDetailService.getAllRefundOrders());

        return orderDetailService.getAllRefundOrders();
    }

    @GetMapping("/getOrderDetailListByOrderId")
    public List<OrderDetailDTO> getOrderDetailListByOrderId(@RequestParam("orderId")String orderId){
        return orderDetailService.getOrderDetailListByOrderId(orderId);
    }



}




