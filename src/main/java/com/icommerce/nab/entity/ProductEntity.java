package com.icommerce.nab.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
