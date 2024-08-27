package com.ohgiraffers.eagles_mocoding.user.service;

import com.ohgiraffers.eagles_mocoding.user.model.dto.UserDTO;
import com.ohgiraffers.eagles_mocoding.user.model.entity.UserEntity;
import com.ohgiraffers.eagles_mocoding.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<UserDTO> allUser() {
        List<UserEntity> entities = userRepository.findAll();
        List<UserDTO> dto = new ArrayList<>();
        if (entities.size() > 0) { // 엔터티에 담긴 값이 있는지 확인
            for (UserEntity entity : entities){
                UserDTO userDTO = new UserDTO();
                userDTO.setId(entity.getId());
                userDTO.setName(entity.getName());
                userDTO.setPostCode(entity.getPostCode());
                userDTO.setDefaultAddress(entity.getDefaultAdd());
                userDTO.setDetailAddress(entity.getDetailAdd());
                dto.add(userDTO);
            }
        }else { // 담긴게 없으면 (데이터가 없으면) null 반환
            return null;
        }
        return dto;
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        // dto에 받아온 값을 엔터티에 저장함
        UserEntity userEntity = new UserEntity.Builder()
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .postCode(userDTO.getPostCode())
                .defaultAdd(userDTO.getDefaultAddress())
                .detailAdd(userDTO.getDetailAddress())
                .build();
        UserEntity savedEntity = userRepository.save(userEntity);

        if(savedEntity != null) { // 데이터가 잘 들어갔으면
            // 잘 들어간 데이터를 다시 savedDTO에 담아줌
            UserDTO savedDTO = new UserDTO();
            savedDTO.setId(savedEntity.getId());
            savedDTO.setName(savedEntity.getName());
            savedDTO.setAge(savedEntity.getAge());
            savedDTO.setPostCode(savedEntity.getPostCode());
            savedDTO.setDefaultAddress(savedEntity.getDefaultAdd());
            savedDTO.setDetailAddress(savedEntity.getDetailAdd());

            return savedDTO;
        } else {
            return null;
        }
    }

    @Transactional
    public UserDTO detail(Integer id){
        Optional<UserEntity> entity = userRepository.findById(id);
        if (entity.isPresent()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(entity.get().getId());
            userDTO.setName(entity.get().getName());
            userDTO.setAge(entity.get().getAge());
            userDTO.setPostCode(entity.get().getPostCode());
            userDTO.setDefaultAddress(entity.get().getDefaultAdd());
            userDTO.setDetailAddress(entity.get().getDetailAdd());
            return userDTO;
        }else {
            return null;
        }
    }

    @Transactional
    public UserDTO modify(Integer id, UserDTO userDTO) {
        Optional<UserEntity> entity = userRepository.findById(id);
        // 수정할 데이터가 있으면
        if(entity.isPresent()) {
            // 업데이트
            UserEntity userEntity = new UserEntity.Builder()
                    .name(userDTO.getName())
                    .age(userDTO.getAge())
                    .postCode(userDTO.getPostCode())
                    .defaultAdd(userDTO.getDefaultAddress())
                    .detailAdd(userDTO.getDetailAddress())
                    .build();
            // 유저엔터티에 저장
            UserEntity savedEntity = userRepository.save(userEntity);
            System.out.println(savedEntity.toString());

            // 저장한 엔터티 확인
            if(savedEntity != null) { // 데이터가 잘 들어갔으면
                // 잘 들어간 데이터를 다시 savedDTO에 담아줌
                UserDTO savedDTO = new UserDTO();
                savedDTO.setId(savedEntity.getId());
                savedDTO.setName(savedEntity.getName());
                savedDTO.setAge(savedEntity.getAge());
                savedDTO.setPostCode(savedEntity.getPostCode());
                savedDTO.setDefaultAddress(savedEntity.getDefaultAdd());
                savedDTO.setDetailAddress(savedEntity.getDetailAdd());

                return savedDTO;
            } else {
                return null;
            }
        }else {
            return null;
        }
    }

    @Transactional
    public int delete(Integer id) {
        Optional<UserEntity> entity = userRepository.findById(id);

        if(entity.isPresent()) { // 조회해온 값이 있으면
            userRepository.deleteById(id); // 데이터 삭제

            // 잘 지워졌는지 다시 조회해봄
            if(!userRepository.findById(id).isPresent()) { // 값이 없으면
                int result = 0;
                return result;
            } else {
                int result = 1;
                return result;
            }
        } else {
            int result = 1;
            return result;
        }
    }
}
