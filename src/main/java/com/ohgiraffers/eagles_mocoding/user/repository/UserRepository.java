package com.ohgiraffers.eagles_mocoding.user.repository;

import com.ohgiraffers.eagles_mocoding.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
