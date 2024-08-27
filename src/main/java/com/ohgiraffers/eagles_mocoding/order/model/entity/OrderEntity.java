package com.ohgiraffers.eagles_mocoding.order.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_sum")
    private Long orderSum;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "default_address")
    private String defaultAddress;

    @Column(name = "detail_address")
    private String detailAddress;


    public OrderEntity() {
    }

    private OrderEntity(Builder builder) {
        this.userId = builder.userId;
        this.orderSum = builder.orderSum;
        this.orderStatus = builder.orderStatus;
        this.defaultAddress = builder.defaultAddress;
        this.detailAddress = builder.detailAddress;
    }

    public static class Builder{
        private Integer userId;
        private Long orderSum;
        private String orderStatus;
        private String defaultAddress;
        private String detailAddress;

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder orderSum(Long orderSum) {
            this.orderSum = orderSum;
            return this;
        }

        public Builder orderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder defaultAddress(String defaultAddress) {
            this.defaultAddress = defaultAddress;
            return this;
        }

        public Builder detailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
            return this;
        }

        public OrderEntity build() {
            return new OrderEntity(this);
        }
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Long getOrderSum() {
        return orderSum;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }
}
