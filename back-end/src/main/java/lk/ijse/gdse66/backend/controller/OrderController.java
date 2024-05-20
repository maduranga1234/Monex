package lk.ijse.gdse66.backend.controller;

import lk.ijse.gdse66.backend.dto.OrderDTO;

import lk.ijse.gdse66.backend.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {

    public OrderController(){
        System.out.println("order working!");
    }

    @Autowired
    private OrderService salesService;

    @PostMapping("/save")
    public void save(@RequestBody OrderDTO orderDTO){
        System.out.println(orderDTO);

        salesService.placeOrder(orderDTO);
    }
}



