package com.ohgiraffers.eagles_mocoding.order.model.dto;

public class OrderDTO {
    private Integer orderId;
    private Integer userId;
    private Long orderSum;
    private String orderStatus;
    private String defaultAddress;
    private String detailAddress;

    public OrderDTO() {
    }

    public OrderDTO(Integer orderId, Integer userId, Long orderSum, String orderStatus, String defaultAddress, String detailAddress) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderSum = orderSum;
        this.orderStatus = orderStatus;
        this.defaultAddress = defaultAddress;
        this.detailAddress = detailAddress;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(Long orderSum) {
        this.orderSum = orderSum;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderSum=" + orderSum +
                ", orderStatus='" + orderStatus + '\'' +
                ", defaultAddress='" + defaultAddress + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                '}';
    }
}
