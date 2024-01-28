package com.everies.order.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ResMsg<T> {
    private Integer status;
    private String message;
    private T data;
}
