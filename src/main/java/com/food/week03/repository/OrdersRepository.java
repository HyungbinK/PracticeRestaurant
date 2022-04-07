package com.food.week03.repository;

import com.food.week03.model.FoodOrder;
import com.food.week03.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository <Orders, Long> {
}
