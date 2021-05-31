package com.icommerce.nab.service;

import com.icommerce.nab.converter.OrderConverter;
import com.icommerce.nab.dto.OrderDTO;
import com.icommerce.nab.entity.OrderEntity;
import com.icommerce.nab.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConverter orderConverter;

    public OrderDTO save(OrderDTO orderDTO) {
        OrderEntity orderEntity;

        if(orderDTO.getId() != null){
            OrderEntity savedOrderEntity = orderRepository.findOne(orderDTO.getId());
            orderEntity = orderConverter.toEntity(orderDTO, savedOrderEntity);
        } else {
            orderEntity = orderConverter.toEntity(orderDTO);
        }

        orderEntity = orderRepository.save(orderEntity);
        return orderConverter.toDTO(orderEntity);
    }

    public void delete(long[] ids) {
        for(long item: ids) {
            orderRepository.delete(item);
        }
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(entity -> orderConverter.toDTO(entity))
                .collect(Collectors.toList());
    }

    public OrderDTO findOneById(long id) {
        OrderEntity orderEntity = orderRepository.findOne(id);
        return orderConverter.toDTO(orderEntity);
    }


}
