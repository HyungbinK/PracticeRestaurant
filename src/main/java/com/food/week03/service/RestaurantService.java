package com.food.week03.service;

import com.food.week03.dto.RestaurantDto;
import com.food.week03.model.Restaurant;
import com.food.week03.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service //사용법이 어떻게 될까
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant registerRestaurants(RestaurantDto requestDto){

        //Getter로 받는 input 요소들
        String name = requestDto.getName();
        Long minOrderPrice = requestDto.getMinOrderPrice();
        Long deliveryFee = requestDto.getDeliveryFee();

        // 조건에 해당하지 않으면 에러가 나야
        if (minOrderPrice < 1000 || minOrderPrice >100000) {
            throw new IllegalArgumentException("최소주문 가격은 1,000원~100,000원입니다") ;
        } else if (minOrderPrice % 100 != 0) {
            throw new IllegalArgumentException("100원 단위로만 가능합니다!");
        } else if (deliveryFee < 0 || deliveryFee >10000) {
            throw new IllegalArgumentException("기본 배달비는 0원~10,000원입니다");
        } else if (deliveryFee % 500 != 0) {
            throw new IllegalArgumentException("500원 단위로만 가능합니다");
        } else if (name.length() < 1) {
            throw new IllegalArgumentException("이름을 입력하세요");
        }

        //파라미터에 바로 dto를 넣는다면?
        // restaurant 객체 안 만들고 name, minOrderPrice, deliveryFee를 바로 save에 넣으면 안되나?
        Restaurant restaurant = new Restaurant(name, minOrderPrice, deliveryFee);
        restaurantRepository.save(restaurant);
        //void로 쓰면 어찌되려나
        return restaurant;
    }

}
