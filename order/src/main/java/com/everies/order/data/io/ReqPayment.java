package com.everies.order.data.io;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReqPayment {
    private String payment_status;
}
