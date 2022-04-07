package com.food.week03.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class FoodOrderDto {
    String name;
    Long price;
    Long quantity;

    public FoodOrderDto(String name, Long price, Long quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
