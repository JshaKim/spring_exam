package com.ohgiraffers.eagles_mocoding.order.controller;

import com.ohgiraffers.eagles_mocoding.order.model.dto.OrderDTO;
import com.ohgiraffers.eagles_mocoding.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/eagles/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> addOrder(@RequestBody OrderDTO orderDTO) {
        Map<String,Object> response = new HashMap<>();

        if (orderDTO.getUserId() == null){
            response.put("error", "사용자 id가 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if (orderDTO.getUserId() <= 0 ){
            response.put("error", "사용자 id는 0보다 작거나 같을 수 없습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if (orderDTO.getOrderSum() == null){
            response.put("error", "주문금액이 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if (orderDTO.getOrderSum() <= 0 ){
            response.put("error", "주문금액은 0보다 작거나 같을 수 없습니다.");
            return ResponseEntity.status(400).body(response);
        }

        System.out.println("(con)orderDTO : " + orderDTO);
        OrderDTO savedDTO = orderService.addOrder(orderDTO);

        if (savedDTO == null) {
            response.put("error", "주문에 실패하였습니다.");
            return ResponseEntity.status(400).body(response);
        } else {
            response.put("orderInfo", orderDTO.toString());
            return ResponseEntity.status(200).body(response);
        }
    }
}
