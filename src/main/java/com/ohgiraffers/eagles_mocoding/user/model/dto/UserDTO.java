package com.ohgiraffers.eagles_mocoding.user.model.dto;

public class UserDTO {
    private Integer id;
    private String name;
    private Integer age;
    private String postCode;
    private String defaultAddress;
    private String detailAddress;

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, Integer age, String postCode, String defaultAddress, String detailAddress) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.postCode = postCode;
        this.defaultAddress = defaultAddress;
        this.detailAddress = detailAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", postCode='" + postCode + '\'' +
                ", defaultAddress='" + defaultAddress + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                '}';
    }
}
