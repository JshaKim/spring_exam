package com.ohgiraffers.eagles_mocoding.user.model.entity;

import com.ohgiraffers.eagles_mocoding.utils.Utils;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "postcode")
    private String postCode;

    @Column(name = "defaultadd")
    private String defaultAdd;

    @Column(name = "detailadd")
    private String detailAdd;

    public UserEntity() {
    }

    private UserEntity(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.postCode = builder.postCode;
        this.defaultAdd = builder.defaultAdd;
        this.detailAdd = builder.detailAdd;
    }
    public static class Builder{
        private String name;
        private Integer age;
        private String postCode;
        private String defaultAdd;
        private String detailAdd;

        public Builder name(String name) {
            if (name.length()>3) {
                throw new IllegalArgumentException("이름은 세 글자 이하로만 가능합니다.");
            } else if (!Utils.regex(name)) {
                throw new IllegalArgumentException("entity:이름은 한국어만 가능합니다.");
            }else {
                this.name = name;
            }
            return this;
        }

        public Builder age(Integer age){
            if (age < 20) {
                throw new IllegalArgumentException("20세 미만은 가입이 불가능합니다.");
            } else {
                this.age = age;
            }

            if(age <= 0) {
                throw new IllegalArgumentException("나이는 0 또는 음수가 될 수 없습니다.");
            } else {
                this.age = age;
            }

            return this;
        }

        public Builder postCode(String postCode){
            if (postCode == null || postCode.trim().length() == 0) {
                throw new IllegalArgumentException("우편번호가 빈 값 입니다.");
            } else {
                this.postCode = postCode;
            }
            return this;
        }

        public Builder defaultAdd(String defaultAdd){
            if (defaultAdd == null || defaultAdd.trim().length() == 0) {
                throw new IllegalArgumentException("기본주소가 빈 값 입니다.");
            } else {
                this.defaultAdd = defaultAdd;
            }
            return this;
        }

        public Builder detailAdd(String detailAdd){
            if (detailAdd == null || detailAdd.trim().length() == 0) {
                throw new IllegalArgumentException("상세주소가 빈 값 입니다.");
            } else {
                this.detailAdd = detailAdd;
            }
            return this;
        }

        public UserEntity build(){
            if (name == null || age == null || postCode == null || defaultAdd == null || detailAdd == null) {
                throw new IllegalArgumentException("입력이 없습니다.");
            }
            return new UserEntity(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getDefaultAdd() {
        return defaultAdd;
    }

    public String getDetailAdd() {
        return detailAdd;
    }
}
