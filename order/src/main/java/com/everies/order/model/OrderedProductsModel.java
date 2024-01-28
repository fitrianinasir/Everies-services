package com.everies.order.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_ordered_products")
public class OrderedProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;


    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "selected_size")
    private String selected_size;

    @Column(name = "selected_variant")
    private String selected_variant;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "total")
    private Integer total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderModel orderModel;
}
