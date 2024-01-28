package com.everies.order.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.Order;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_id")
    private Integer customer_id;

    @Column(name = "checkout_time")
    private LocalDateTime checkout_time;

    @Column(name = "total_payment")
    private Integer total_payment;

    @Column(name = "payment_type")
    private String payment_type;

    @Column(name = "payment_time")
    private LocalDateTime payment_time;

    @Column(name = "payment_status")
    private String payment_status;

    @Column(name = "notif_time")
    private LocalDateTime notif_time;

    @Column(name = "notif_status")
    private String notif_status;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "orderModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderedProductsModel> ordered_products = new ArrayList<>();
}
