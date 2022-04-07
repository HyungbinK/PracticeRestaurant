package com.food.week03.service;

import com.food.week03.dto.FoodDto;
import com.food.week03.model.Food;
import com.food.week03.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional
    //메뉴등록
    public void registerMenu(Long restaurantId, List<FoodDto> requestDto) {

//        Long price = requestDto.getPrice();
//        String name = requestDto.getName();

        for (FoodDto foodDto : requestDto) {
            Food menu = new Food(restaurantId, foodDto);
            //가격
            if (foodDto.getPrice() < 100 || foodDto.getPrice() > 1000000) {
                throw new IllegalArgumentException("가격은 100원 이상 1,000,000원 이하입니다");
            } else if (foodDto.getPrice() % 100 != 0) {
                throw new IllegalArgumentException("100원 단위로만 입력 가능합니다");
            }
            //음식명
            //List의 역할을 못하는듯
            List<Food> foodList = foodRepository.findByRestaurantIdAndName(restaurantId, foodDto.getName());
            if (foodList.size() > 0) {
                throw new IllegalArgumentException();
            }

            foodRepository.save(menu);
        }




    }
}
