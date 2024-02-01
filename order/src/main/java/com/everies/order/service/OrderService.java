package com.everies.order.service;

import com.everies.order.data.dto.OrderDTO;
import com.everies.order.data.dto.OrderedProductsDTO;
import com.everies.order.data.io.ReqNotif;
import com.everies.order.data.io.ReqOrder;
import com.everies.order.model.OrderModel;
import com.everies.order.model.OrderedProductsModel;
import com.everies.order.repository.OrderRepo;
import com.everies.order.repository.OrderedProductsRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public OrderService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

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

    public void pushNotification(ReqNotif notif){
        String templateName = "email-body";
        notif.setCustomer_name("Fitriani Nasir");
        notif.setCustomer_email("entftr@gmail.com");

        System.out.println(notif);

        Context context = new Context();
        context.setVariable("customer", notif.getCustomer_name());
        context.setVariable("payment_type", notif.getPayment_name());
        context.setVariable("payment_time", notif.getPayment_time());
        context.setVariable("products", notif.getOrdered_products());
        context.setVariable("total", notif.getTotal_payment());


        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, "UTF-8");
        try{
            helper.setTo(notif.getCustomer_email());
            helper.setSubject("Your Everies Order");
            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);
            javaMailSender.send(msg);

        }catch (MessagingException e){
            System.out.println(e);
        }

    }
}
