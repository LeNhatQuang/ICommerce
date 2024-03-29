package com.icommerce.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String category;

    @Column
    private String image;

    @OneToMany(mappedBy = "product")
    private List<ProductOrderEntity> productOrders;
}
