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
@Table(name = "tbl_payment_options")
public class PaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "payment_type")
    private String payment_type;


    @Column(name = "payment_name")
    private String payment_name;

    @Column(name = "payment_number")
    private Integer payment_number;

    @Column(name = "payment_icon")
    private String payment_icon;
}
