package com.food.week03.controller;

import com.food.week03.dto.FoodDto;
import com.food.week03.model.Food;
import com.food.week03.repository.FoodRepository;
import com.food.week03.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodRepository foodRepository;
    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerMenu(@PathVariable Long restaurantId, @RequestBody List<FoodDto> requestDto) {
        foodService.registerMenu(restaurantId, requestDto);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> viewMenu(@PathVariable Long restaurantId){
        return foodRepository.findByRestaurantId(restaurantId);
    }
}
