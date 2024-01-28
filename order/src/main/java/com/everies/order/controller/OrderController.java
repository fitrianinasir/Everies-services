package com.everies.order.controller;

import com.everies.order.dto.ReqOrder;
import com.everies.order.dto.ResMsg;
import com.everies.order.model.OrderModel;
import com.everies.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<ResMsg> getAllOrders(){
        List<OrderModel> orders = orderService.getAllOrders();
        ResMsg resMsg = new ResMsg(200, "Data retrieved successfully", orders);
        return new ResponseEntity<>(resMsg, HttpStatus.OK);
    }

    @PostMapping("/order")
    public void pushOrder(@RequestBody ReqOrder reqOrder) throws JsonProcessingException {
        orderService.createOrder(reqOrder);
    }
}
