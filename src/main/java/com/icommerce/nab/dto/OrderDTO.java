package com.icommerce.nab.dto;

import com.icommerce.nab.entity.ProductEntity;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO extends AbstractDTO {
    private String fullName;
    private String phoneNumber;
    private String address;
    private String email;
    private List<ProductOrderDTO> productOrders;
}
