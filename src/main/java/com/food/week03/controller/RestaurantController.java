package com.food.week03.controller;

import com.food.week03.dto.RestaurantDto;
import com.food.week03.model.Restaurant;
import com.food.week03.repository.RestaurantRepository;
import com.food.week03.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    //음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurants(@RequestBody RestaurantDto requestDto) {

        return restaurantService.registerRestaurants(requestDto);
    }

    //음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> viewRestaurants() { // List<>에 대해 공부해보자
        return restaurantRepository.findAll();
    }
}
