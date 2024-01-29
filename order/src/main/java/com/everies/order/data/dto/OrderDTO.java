package com.everies.order.data.dto;

import com.everies.order.model.OrderModel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDTO {
    private Integer id;
    private Integer customer_id;
    private LocalDateTime checkout_time;
    private Integer total_payment;
    private String payment_type;
    private LocalDateTime payment_time;
    private String payment_status;
    private LocalDateTime notif_time;
    private String notif_status;
    private String notes;
    private List<OrderedProductsDTO> ordered_products;

    public OrderDTO(OrderModel orderModel, List<OrderedProductsDTO> orderedProducts){
        this.id = orderModel.getId();
        this.customer_id = orderModel.getCustomer_id();
        this.checkout_time = orderModel.getCheckout_time();
        this.total_payment = orderModel.getTotal_payment();
        this.payment_type = orderModel.getPayment_type();
        this.payment_time = orderModel.getPayment_time();
        this.payment_status = orderModel.getPayment_status();
        this.notif_time = orderModel.getNotif_time();
        this.notif_status = orderModel.getNotif_status();
        this.notes = orderModel.getNotes();
        this.ordered_products = orderedProducts;

    }

    public OrderDTO(Optional<OrderModel> orderModel, List<OrderedProductsDTO> orderedProducts) {
        this.id = orderModel.get().getId();
        this.customer_id = orderModel.get().getCustomer_id();
        this.checkout_time = orderModel.get().getCheckout_time();
        this.total_payment = orderModel.get().getTotal_payment();
        this.payment_type = orderModel.get().getPayment_type();
        this.payment_time = orderModel.get().getPayment_time();
        this.payment_status = orderModel.get().getPayment_status();
        this.notif_time = orderModel.get().getNotif_time();
        this.notif_status = orderModel.get().getNotif_status();
        this.notes = orderModel.get().getNotes();
        this.ordered_products = orderedProducts;
    }
}
