package com.food.week03.model;

import com.food.week03.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @OneToMany
    @JoinColumn(name = "FOOD_ID")
    private List<FoodOrder> foods;

    @Column(nullable = false)
    private Long totalPrice;

    @Column
    private Long deliveryFee;

    public Orders(String restaurantName, List<FoodOrder> foods, Long totalPrice, Long deliveryFee){
        this.restaurantName = restaurantName;
        this.foods = foods;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;

    }


}
