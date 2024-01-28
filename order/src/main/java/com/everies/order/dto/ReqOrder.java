package com.everies.order.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReqOrder {
    private Integer customer_id;
    private String ordered_products;
    private Integer total_payment;
    private String payment_type;
    private LocalDateTime payment_time;
    private String payment_status;
    private LocalDateTime notif_time;
    private String notif_status;
    private String notes;
}
