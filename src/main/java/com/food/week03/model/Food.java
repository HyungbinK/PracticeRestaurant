package com.food.week03.model;

import com.food.week03.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Food {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    //생성자1
    public Food(FoodDto requestDto){
        this.restaurantId = requestDto.getRestaurantId();
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
    }

    //생성자2
    public Food(Long restaurantId, String name, Long price) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
    }

    //생성자3
    public Food(Long restaurantId, FoodDto requestDto) {
        this.restaurantId = restaurantId;
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
    }
}
