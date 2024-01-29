package com.everies.order.data.io;

import com.everies.order.data.dto.DTOOrderedProduct;
import com.everies.order.data.dto.OrderedProductsDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReqNotif {
    private Integer order_id;
    private Integer customer_id;
    private String customer_name;
    private String customer_email;
    private String payment_name;
    private Integer total_payment;
    private LocalDateTime payment_time;
    private String status_payment;
    private List<OrderedProductsDTO> ordered_products;
}
