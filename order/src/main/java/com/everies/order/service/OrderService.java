package com.everies.order.service;

import com.everies.order.data.dto.OrderDTO;
import com.everies.order.data.dto.OrderedProductsDTO;
import com.everies.order.data.io.ReqOrder;
import com.everies.order.model.OrderModel;
import com.everies.order.model.OrderedProductsModel;
import com.everies.order.repository.OrderRepo;
import com.everies.order.repository.OrderedProductsRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderedProductsRepo orderedProductsRepo;

    public List<OrderDTO> getAllOrders(){
        List<OrderModel> orders = orderRepo.findAll();
        List<OrderDTO> result = new ArrayList<>();

        for(OrderModel order: orders){
            List<OrderedProductsDTO> orderedProducts = new ArrayList<>();
            for(OrderedProductsModel oprod: order.getOrdered_products()){
                OrderedProductsDTO data = new OrderedProductsDTO(oprod);
                orderedProducts.add(data);
            }
            OrderDTO data = new OrderDTO(order, orderedProducts);
            result.add(data);
        }
        return result;
    }

    public OrderDTO getOrderById(Integer id){
        Optional<OrderModel> order = orderRepo.findById(id);
        List<OrderedProductsDTO> orderedProducts = new ArrayList<>();
        for(OrderedProductsModel oprod : order.get().getOrdered_products()){
           OrderedProductsDTO data = new OrderedProductsDTO(oprod);
           orderedProducts.add(data);
        }

        OrderDTO result = new OrderDTO(order, orderedProducts);
        return result;
    }
    public OrderDTO createOrder(ReqOrder reqOrder) throws JsonProcessingException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        OrderModel orderModel = new OrderModel();
        orderModel.setCustomer_id(reqOrder.getCustomer_id());
        orderModel.setCheckout_time(currentDateTime);
        orderModel.setTotal_payment(reqOrder.getTotal_payment());
        orderModel.setPayment_type(reqOrder.getPayment_type());
        orderModel.setPayment_time(null);
        orderModel.setPayment_status("WAITING PAYMENT");
        orderModel.setNotif_time(null);
        orderModel.setNotif_status("WAITING");
        orderModel.setNotes(reqOrder.getNotes());

        OrderModel order = orderRepo.save(orderModel);

        ObjectMapper objectMapper = new ObjectMapper();
        OrderedProductsModel[] orderedProducts = objectMapper.readValue(reqOrder.getOrdered_products(), OrderedProductsModel[].class);
        OrderedProductsDTO[] orderedProductsRes = objectMapper.readValue(reqOrder.getOrdered_products(), OrderedProductsDTO[].class);
        Integer count = 0;
        for(OrderedProductsModel obj : orderedProducts){
            obj.setOrderModel(order);
            OrderedProductsModel res = orderedProductsRepo.save(obj);
            orderedProductsRes[count].setId(res.getId());
            count+=1;
        }

        OrderDTO result = new OrderDTO(order, List.of(orderedProductsRes));
        return result;
    }

    public boolean doPayment(Integer orderId, String paymentStatus){
        LocalDateTime paymentTime = LocalDateTime.now();
        orderRepo.updatePayment(orderId, paymentStatus, paymentTime);
        return true;
    }

    private void pushNotification(){
//
    }
}
