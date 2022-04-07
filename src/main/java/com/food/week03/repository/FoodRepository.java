package com.food.week03.repository;

import com.food.week03.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByRestaurantIdAndName(Long restaurantId, String name);
    List<Food> findByRestaurantId(Long restaurantId);
}
