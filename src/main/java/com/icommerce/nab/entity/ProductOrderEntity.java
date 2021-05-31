package com.icommerce.nab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_order")
public class ProductOrderEntity {
    @EmbeddedId
    ProductOrderKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    ProductEntity product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    OrderEntity order;

    @Column
    int number;
}
