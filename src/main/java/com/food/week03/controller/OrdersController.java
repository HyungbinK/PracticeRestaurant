package com.food.week03.controller;

import com.food.week03.dto.OrderDto;
import com.food.week03.dto.OrderRequestDto;
import com.food.week03.model.Orders;
import com.food.week03.repository.OrdersRepository;
import com.food.week03.service.FoodService;
import com.food.week03.service.OrdersService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrdersController {

    private final OrdersRepository ordersRepository;
    private final OrdersService ordersService;

    @PostMapping("/order/request")
    public OrderDto orderMenu (@RequestBody OrderRequestDto orderRequestDto){

        return ordersService.orderMenu(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<Orders> getOrderList(){
        return ordersService.getOrderList();
    }
}
