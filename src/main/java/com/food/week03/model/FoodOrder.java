package com.food.week03.model;

import com.food.week03.dto.FoodDto;
import com.food.week03.dto.FoodOrderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class FoodOrder {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    public FoodOrder(String name, Long price, Long quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }

    }

    // 1) 주문 음식점 이름 2) 배달비 -> restaurant id 필요하려나
    // 3)주문 음식명, 4)수량, 5)가격 -> food id?

