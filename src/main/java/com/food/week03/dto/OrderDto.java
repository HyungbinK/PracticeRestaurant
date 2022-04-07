package com.food.week03.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDto {
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private Long deliveryFee;
    private Long totalPrice;

    public OrderDto(String restaurantName, List<FoodOrderDto> foodOrderDtoList, Long totalPrice, Long deliveryFee) {
        this.restaurantName = restaurantName;
        this.foods = foodOrderDtoList;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
    }
}
