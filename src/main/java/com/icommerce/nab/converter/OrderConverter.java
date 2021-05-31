package com.icommerce.nab.converter;

import com.icommerce.nab.dto.OrderDTO;
import com.icommerce.nab.dto.ProductOrderDTO;
import com.icommerce.nab.entity.OrderEntity;
import com.icommerce.nab.entity.ProductOrderEntity;
import com.icommerce.nab.entity.ProductOrderKey;
import com.icommerce.nab.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {
    @Autowired
    private ProductRepository productRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public OrderEntity toEntity(OrderDTO orderDTO) {
        OrderEntity orderEntity = modelMapper.map(orderDTO, OrderEntity.class);
        List<ProductOrderEntity> productOrders = buildProductOrderEntities(orderDTO, orderEntity);
        orderEntity.setProductOrders(productOrders);
        return orderEntity;
    }

    public OrderEntity toEntity(OrderDTO orderDTO, OrderEntity orderEntity) {
        orderEntity = modelMapper.map(orderDTO, OrderEntity.class);
        List<ProductOrderEntity> productOrders = buildProductOrderEntities(orderDTO, orderEntity);
        orderEntity.setProductOrders(productOrders);
        return orderEntity;
    }

    public OrderDTO toDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = modelMapper.map(orderEntity, OrderDTO.class);
        List<ProductOrderDTO> productOrders = orderEntity.getProductOrders()
                .stream()
                .map(productOrder -> ProductOrderDTO
                        .builder()
                        .productId(productOrder.getProduct().getId())
                        .number(productOrder.getNumber())
                        .build())
                .collect(Collectors.toList());
        orderDTO.setProductOrders(productOrders);
        return orderDTO;
    }

    private List<ProductOrderEntity> buildProductOrderEntities(OrderDTO orderDTO, OrderEntity orderEntity) {
        return orderDTO.getProductOrders()
                .stream()
                .map(productOrder -> ProductOrderEntity
                        .builder()
                        .id(ProductOrderKey
                                .builder()
                                .orderId(orderEntity.getId())
                                .productId(productOrder.getProductId())
                                .build())
                        .order(orderEntity)
                        .product(productRepository.getOne(productOrder.getProductId()))
                        .number(productOrder.getNumber())
                        .build())
                .collect(Collectors.toList());
    }
}
