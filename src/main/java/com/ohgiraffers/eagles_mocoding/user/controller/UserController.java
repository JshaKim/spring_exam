package com.ohgiraffers.eagles_mocoding.user.controller;

import com.ohgiraffers.eagles_mocoding.user.model.dto.UserDTO;
import com.ohgiraffers.eagles_mocoding.user.service.UserService;
import com.ohgiraffers.eagles_mocoding.utils.Utils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/eagles/user")
public class UserController {

    private final HttpServletResponse httpServletResponse;
    private UserService userService;

    public UserController(UserService userService, HttpServletResponse httpServletResponse) {
        this.userService = userService;
        this.httpServletResponse = httpServletResponse;
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> allUser() {
        Map<String, Object> response = new HashMap<>();

        List<UserDTO> userList = userService.allUser();
        if (userList != null){ // userList에 값이 있으면
            response.put("userInfo", userList);
            return ResponseEntity.ok(response);
        }else {
            response.put("error", "회원목록 데이터가 없습니다.");
            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserDTO userDTO){
        Map<String, Object> response = new HashMap<>();

        if(userDTO.getName() == null || userDTO.getName().isEmpty()) { // 이름을 입력하지 않았을경우
            response.put("error", "이름이 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if(userDTO.getAge() == null) { // 나이를 입력하지 않았을경우
            response.put("error", "나이가 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if(userDTO.getPostCode() == null || userDTO.getPostCode().isEmpty()) { // 우편번호를 입력하지 않았을경우
            response.put("error", "우편번호가 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if(userDTO.getDefaultAddress() == null || userDTO.getDefaultAddress().isEmpty()) { // 기본주소를 입력하지 않았을경우
            response.put("error", "기본주소가 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if(userDTO.getDetailAddress() == null || userDTO.getDetailAddress().isEmpty()) { // 상세주소를 입력하지 않았을경우
            response.put("error", "상세주소가 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if(userDTO.getName().length() > 3) { // 이름이 세글자가 넘으면
            response.put("error", "이름은 세글자 이상 지원이 불가능합니다.");
            return ResponseEntity.status(400).body(response);
        }
        if(!Utils.regex(userDTO.getName())){
            response.put("error", "con:이름은 한국어만 가능합니다.");
            return ResponseEntity.status(400).body(response);
        }
        if (userDTO.getAge()<20) { // 나이가 20살이 안되면
            response.put("error", "20세 미만은 가입이 불가능합니다.");
            return ResponseEntity.status(400).body(response);
        }

        UserDTO savedDTO = userService.createUser(userDTO); // dto를 서비스로 넘김

        if(savedDTO != null) {
            System.out.println(userDTO.toString());
            response.put("result", savedDTO);
            response.put("message","회원가입 성공");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "회원가입 실패. 다시 시도하세요");
            return ResponseEntity.status(400).body(response);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Map<String,Object>> detail(@PathVariable Integer id){
        Map<String,Object> response = new HashMap<>();

        if(id <= 0 || id == null) {
            response.put("error", "다시 시도해주세요.");
            return ResponseEntity.status(400).body(response);
        }

        UserDTO findDTO = userService.detail(id);
        if (findDTO != null) {
            System.out.println(findDTO.toString());
            response.put("userInfo", findDTO);
            return ResponseEntity.ok(response);
        }else {
            response.put("error", "조회 실패. 다시 시도해주세요.");
            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<Map<String,Object>> modify(@RequestBody UserDTO userDTO,@PathVariable Integer id){
        Map<String,Object> response = new HashMap<>();

        if(id<=0 || id == null) {
            response.put("error", "다시 시도해주세요.");
            return ResponseEntity.status(400).body(response);
        }

        if(userDTO.getName() == null || userDTO.getName().isEmpty()) { // 이름을 입력하지 않았을경우
            response.put("error", "이름이 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if(userDTO.getAge() == null) { // 나이를 입력하지 않았을경우
            response.put("error", "나이가 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if(userDTO.getPostCode() == null || userDTO.getPostCode().isEmpty()) { // 우편번호를 입력하지 않았을경우
            response.put("error", "우편번호가 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if(userDTO.getDefaultAddress() == null || userDTO.getDefaultAddress().isEmpty()) { // 기본주소를 입력하지 않았을경우
            response.put("error", "기본주소가 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if(userDTO.getDetailAddress() == null || userDTO.getDetailAddress().isEmpty()) { // 상세주소를 입력하지 않았을경우
            response.put("error", "상세주소가 입력되지 않았습니다.");
            return ResponseEntity.status(400).body(response);
        }
        if(userDTO.getName().length() > 3) { // 이름이 세글자가 넘으면
            response.put("error", "이름은 세글자 이상 지원이 불가능합니다.");
            return ResponseEntity.status(400).body(response);
        }

        if(!Utils.regex(userDTO.getName())){
            response.put("error", "이름은 한국어만 가능합니다.");
            return ResponseEntity.status(400).body(response);
        }
        if (userDTO.getAge()<20) { // 나이가 20살이 안되면
            response.put("error", "20세 미만은 가입이 불가능합니다.");
            return ResponseEntity.status(400).body(response);
        }

        UserDTO modifyDTO = userService.modify(id,userDTO);

        if(modifyDTO != null) {
            response.put("modifyUserInfo", modifyDTO);
            response.put("message", "회원정보수정이 완료되었습니다.");
            return ResponseEntity.ok(response);
        }else {
            response.put("message", "수정에 실패했습니다. 다시 시도해주세요.");
            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();

        int result = userService.delete(id);

        if (result == 0) { // 데이터가 잘 지워졌으면
            response.put("message", "삭제 성공");
            return ResponseEntity.ok(response);
        }else {
            response.put("error", "삭제 실패");
            return ResponseEntity.status(400).body(response);
        }
    }
}
