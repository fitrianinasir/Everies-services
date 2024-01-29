package com.everies.order.data.dto;

import com.everies.order.model.OrderedProductsModel;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderedProductsDTO {
    private Integer id;
    private Integer product_id;
    private String product_name;
    private String selected_size;
    private String selected_variant;
    private Integer amount;
    private Integer total;

    public OrderedProductsDTO(OrderedProductsModel oprod){
        this.id = oprod.getId();
        this.product_id = oprod.getProduct_id();
        this.product_name = oprod.getProduct_name();
        this.selected_size = oprod.getSelected_size();
        this.selected_variant = oprod.getSelected_variant();
        this.amount = oprod.getAmount();
        this.total = oprod.getTotal();
    }
}
