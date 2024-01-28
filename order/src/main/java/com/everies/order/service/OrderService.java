package com.everies.order.service;

import com.everies.order.dto.DTOOrderedProduct;
import com.everies.order.dto.ReqOrder;
import com.everies.order.model.OrderModel;
import com.everies.order.model.OrderedProductsModel;
import com.everies.order.repository.OrderRepo;
import com.everies.order.repository.OrderedProductsRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderedProductsRepo orderedProductsRepo;

    public List<OrderModel> getAllOrders(){
        return orderRepo.findAll();
    }

    public OrderModel createOrder(ReqOrder reqOrder) throws JsonProcessingException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        OrderModel orderModel = new OrderModel();
        orderModel.setCustomer_id(reqOrder.getCustomer_id());
        orderModel.setCheckout_time(currentDateTime);
        orderModel.setTotal_payment(reqOrder.getTotal_payment());
        orderModel.setPayment_type(reqOrder.getPayment_type());
        orderModel.setPayment_time(null);
        orderModel.setPayment_status("Waiting");
        orderModel.setNotif_time(null);
        orderModel.setNotif_status("Waiting");
        orderModel.setNotes(reqOrder.getNotes());

        OrderModel order = orderRepo.save(orderModel);

        ObjectMapper objectMapper = new ObjectMapper();
        OrderedProductsModel[] orderedProducts = objectMapper.readValue(reqOrder.getOrdered_products(), OrderedProductsModel[].class);
        for(OrderedProductsModel obj : orderedProducts){
            obj.setOrder_Id(order.getId());
            orderedProductsRepo.save(obj);
        }

        return order;
    }



}
