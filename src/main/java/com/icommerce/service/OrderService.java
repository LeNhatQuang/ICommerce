package com.icommerce.service;

import com.icommerce.converter.OrderConverter;
import com.icommerce.dto.OrderDTO;
import com.icommerce.entity.OrderEntity;
import com.icommerce.entity.UserEntity;
import com.icommerce.repository.OrderRepository;
import com.icommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private UserRepository userRepository;

    public OrderDTO save(OrderDTO orderDTO) {
        OrderEntity orderEntity;

        if(orderDTO.getId() != null){
            OrderEntity savedOrderEntity = orderRepository.findById(orderDTO.getId()).get();
            orderEntity = orderConverter.toEntity(orderDTO, savedOrderEntity);
        } else {
            orderEntity = orderConverter.toEntity(orderDTO);
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.getOne(userDetails.getId());
        orderEntity.setUser(userEntity);

        orderEntity = orderRepository.save(orderEntity);
        return orderConverter.toDTO(orderEntity);
    }

    public void delete(long[] ids) {
        for(long item: ids) {
            orderRepository.deleteById(item);
        }
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(entity -> orderConverter.toDTO(entity))
                .collect(Collectors.toList());
    }

    public OrderDTO findOneById(long id) {
        OrderEntity orderEntity = orderRepository.findById(id).get();
        return orderConverter.toDTO(orderEntity);
    }


}
