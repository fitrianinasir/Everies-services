package com.everies.products.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryDTO {
    private String title;
    private String type;
    private String img;
    private Integer order;
}
