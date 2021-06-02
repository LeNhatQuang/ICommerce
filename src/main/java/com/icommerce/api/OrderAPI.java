package com.icommerce.api;

import com.icommerce.dto.OrderDTO;
import com.icommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OrderAPI {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/order")
    public OrderDTO createOrder(@RequestBody OrderDTO model) {
        return orderService.save(model);
    }

    @PutMapping(value = "/order/{id}")
    public OrderDTO updateOrder(@RequestBody OrderDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return orderService.save(model);
    }

    @DeleteMapping(value = "/order")
    public void deleteOrder(@RequestBody long[] ids) {
        orderService.delete(ids);
    }

    @GetMapping(value = "/order/{id}")
    public OrderDTO getOrderById(@PathVariable("id") long id) {
        return orderService.findOneById(id);
    }

    @GetMapping(value = "/orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.findAll();
    }
}