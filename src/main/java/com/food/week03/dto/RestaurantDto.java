package com.food.week03.dto;

import lombok.Getter;

@Getter
public class RestaurantDto {
    private Long id;
    private String name;
    private Long minOrderPrice;
    private Long deliveryFee;
}
