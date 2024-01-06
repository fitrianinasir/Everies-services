package com.everies.products.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_SUB_CATEGORY")
public class SubCategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "category_id")
    private Integer category_id;

    @Column(name = "title")
    private String title;
}
