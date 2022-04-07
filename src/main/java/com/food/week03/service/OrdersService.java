package com.food.week03.service;

import com.food.week03.dto.FoodOrderDto;
import com.food.week03.dto.FoodOrderRequestDto;
import com.food.week03.dto.OrderDto;
import com.food.week03.dto.OrderRequestDto;
import com.food.week03.model.Food;
import com.food.week03.model.FoodOrder;
import com.food.week03.model.Orders;
import com.food.week03.model.Restaurant;
import com.food.week03.repository.FoodOrderRepository;
import com.food.week03.repository.FoodRepository;
import com.food.week03.repository.OrdersRepository;
import com.food.week03.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderRepository foodOrderRepository;

    @Transactional
    public OrderDto orderMenu(OrderRequestDto orderRequestDto) {
        //1)음식점 이름 체크
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(
                        () -> new IllegalArgumentException("음식점 이름을 확인해주세요")
                );

        //테이블에 저장할 주문음식 이름, 수량, 가격
        List<FoodOrder> foodOrderList = new ArrayList<>();

        //주문자에게 보여줄 주문음식 이름, 수량, 가격
        List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();

        //배달비 포함 totalPrice 변수
        Long totalPrice = 0L;
        Long deliveryFee = restaurant.getDeliveryFee();
        String restaurantName = restaurant.getName();


        for (FoodOrderRequestDto foodOrderRequestDto : orderRequestDto.getFoods()) {
            //2)음식 정보들 체크
            //2-1) 음식수량 체크
            Long quantity = foodOrderRequestDto.getQuantity();
            if (quantity < 0 || quantity > 100) {
                throw new IllegalArgumentException("음식은 1개 이상 100개 이하만 주문 가능합니다");
            }
            //2-2) 음식 id로 음식정보 체크
            Food food = foodRepository.findById(foodOrderRequestDto.getId())
                    .orElseThrow(
                            () -> new IllegalArgumentException("해당음식이 없습니다")
                    );
            //2-3) 음식정보 관련 변수 선언
            String name = food.getName();
            Long price = food.getPrice() * quantity;

            totalPrice += price;

            //3) 값 넣어주기
            //3-1)FoodOrder 리스트에 값 넣어주기
            FoodOrder foodOrder = new FoodOrder(name, price, quantity);
            foodOrderList.add(foodOrder);
            foodOrderRepository.save(foodOrder);

            //3-2)FoodOrderDto 리스트에 값 넣어주기
            //repo에 저장은 따로 하지 않음(리턴용이므로)
            FoodOrderDto foodOrderDto = new FoodOrderDto(name, price, quantity);
            foodOrderDtoList.add(foodOrderDto);
        }

        if (restaurant.getMinOrderPrice() > totalPrice) {
            throw new IllegalArgumentException("최소 주문가격을 맞춰주세요.");
        }

        totalPrice += deliveryFee;

        // 리턴해줄 OrderDto 생성
        OrderDto orderList = new OrderDto(restaurantName, foodOrderDtoList, totalPrice,deliveryFee);
        // Orders 테이블에 넣어줄 Orders 객체 생성 후 레포지토리에 저장
        Orders orders = new Orders(restaurantName, foodOrderList, totalPrice, deliveryFee);
        ordersRepository.save(orders);

        return orderList;

    }

    // 주문 조회하기
    public List<Orders> getOrderList() {
        return ordersRepository.findAll();

    }
}

