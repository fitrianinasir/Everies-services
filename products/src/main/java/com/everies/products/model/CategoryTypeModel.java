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
@Table(name = "TBL_CATEGORY_TYPE")
public class CategoryTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
