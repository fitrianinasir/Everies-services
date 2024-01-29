package com.everies.order.data.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DTOOrderedProduct {
    private Integer product_id;
    private String product_name;
    private String selected_size;
    private String selected_variant;
    private Integer amount;
    private Integer total;
}
