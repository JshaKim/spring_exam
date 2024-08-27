package com.ohgiraffers.eagles_mocoding.order.service;

import com.ohgiraffers.eagles_mocoding.order.model.dto.OrderDTO;
import com.ohgiraffers.eagles_mocoding.order.model.entity.OrderEntity;
import com.ohgiraffers.eagles_mocoding.order.repository.OrderRepository;
import com.ohgiraffers.eagles_mocoding.user.model.dto.UserDTO;
import com.ohgiraffers.eagles_mocoding.user.model.entity.UserEntity;
import com.ohgiraffers.eagles_mocoding.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public OrderDTO addOrder(OrderDTO orderDTO) {
        System.out.println("orderDTO.getUserId() : " + orderDTO.getUserId());
        Optional<UserEntity> findUser = userRepository.findById(orderDTO.getUserId()); // 주소를 가져오기 위해 userId 기준으로 데이터 찾음
        System.out.println(findUser.toString());
        if (findUser.get().getId() == orderDTO.getUserId()){
            OrderEntity orderEntity = new OrderEntity.Builder()
                    .userId(orderDTO.getUserId())
                    .orderSum(orderDTO.getOrderSum())
                    .orderStatus("ordered")
                    .defaultAddress(findUser.get().getDefaultAdd())
                    .detailAddress(findUser.get().getDetailAdd())
                    .build();
            OrderEntity savedEntity = orderRepository.save(orderEntity);
            System.out.println("ser)orderEntity : " + savedEntity.toString());

            if (savedEntity != null){ // 데이터가 잘 들어갔으면
                OrderDTO savedDTO = new OrderDTO();
                savedDTO.setOrderId(savedEntity.getOrderId());
                savedDTO.setUserId(savedEntity.getUserId());
                savedDTO.setOrderSum(savedEntity.getOrderSum());
                savedDTO.setOrderStatus(savedEntity.getOrderStatus());
                savedDTO.setDefaultAddress(savedEntity.getDefaultAddress());
                savedDTO.setDetailAddress(savedEntity.getDetailAddress());

                return savedDTO;
            } else {
                return null;
            }
        } else{
            return null;
        }
    }

}
