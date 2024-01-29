package com.everies.order.data.io;

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
