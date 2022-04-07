package com.food.week03.repository;

import com.food.week03.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository <FoodOrder, Long> {
}
