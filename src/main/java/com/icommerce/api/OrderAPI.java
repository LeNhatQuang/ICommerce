package com.icommerce.api;

import com.icommerce.dto.OrderDTO;
import com.icommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderAPI {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO model) {
        return orderService.save(model);
    }

    @PutMapping(value = "/{id}")
    public OrderDTO updateOrder(@RequestBody OrderDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return orderService.save(model);
    }

    @DeleteMapping
    public void deleteOrder(@RequestBody long[] ids) {
        orderService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public OrderDTO getOrderById(@PathVariable("id") long id) {
        return orderService.findOneById(id);
    }

    @GetMapping(value = "/all")
    public List<OrderDTO> getAllOrders() {
        return orderService.findAll();
    }
}