package com.icommerce.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "\"order\"")
public class OrderEntity extends BaseEntity {

    @Column
    private String fullName;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String email;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ProductOrderEntity> productOrders;

    @ManyToOne
    private UserEntity user;
}
