package com.everies.products.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_PRODUCT")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_id")
    private Integer category_id;

    @Column(name = "type_id")
    private Integer type_id;

    @Column(name = "title")
    private String title;

    @Column(name = "img")
    private String img;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stock_by_type")
    private String stock_by_type;

    @Column(name = "stock_by_size")
    private String stock_by_size;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "sold")
    private Integer sold;
}
