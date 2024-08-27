package com.ohgiraffers.eagles_mocoding.order.repository;

import com.ohgiraffers.eagles_mocoding.order.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
