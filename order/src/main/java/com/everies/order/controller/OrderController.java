package com.everies.order.controller;

import com.everies.order.data.dto.OrderDTO;
import com.everies.order.data.io.ReqNotif;
import com.everies.order.data.io.ReqOrder;
import com.everies.order.data.io.ReqPayment;
import com.everies.order.data.io.ResMsg;
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
        List<OrderDTO> orders = orderService.getAllOrders();
        ResMsg resMsg = new ResMsg(200, "Data retrieved successfully", orders);
        return new ResponseEntity<>(resMsg, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<ResMsg> getOrderById(@PathVariable("id") Integer id){
        OrderDTO order = orderService.getOrderById(id);
        ResMsg resMsg = new ResMsg(200, "Data retrieved successfully", order);
        return new ResponseEntity<>(resMsg, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> pushOrder(@RequestBody ReqOrder reqOrder) throws JsonProcessingException {
        OrderDTO result = orderService.createOrder(reqOrder);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<ResMsg> doPayment(@PathVariable("id") Integer id, @RequestBody ReqPayment reqPayment){
        Boolean result = orderService.doPayment(id, reqPayment.getPayment_status());

        OrderDTO orderData = orderService.getOrderById(id);

        ReqNotif notif = new ReqNotif();
        notif.setOrder_id(orderData.getId());
        notif.setCustomer_id(orderData.getCustomer_id());
        notif.setPayment_name(orderData.getPayment_type());
        notif.setPayment_time(orderData.getPayment_time());
        notif.setTotal_payment(orderData.getTotal_payment());
        notif.setStatus_payment(orderData.getPayment_status());
        notif.setOrdered_products(orderData.getOrdered_products());

        orderService.pushNotification(notif);

        ResMsg response = new ResMsg();
        if(result){
            response.setStatus(200);
            response.setMessage("Payment updated");
            response.setData(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.setStatus(400);
            response.setMessage("Payment failed");
            response.setData(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
