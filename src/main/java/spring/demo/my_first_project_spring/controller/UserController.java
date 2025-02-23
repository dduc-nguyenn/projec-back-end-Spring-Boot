package spring.demo.my_first_project_spring.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import spring.demo.my_first_project_spring.dto.request.ApiResponse;
import spring.demo.my_first_project_spring.dto.request.UserCreateRequest;
import spring.demo.my_first_project_spring.dto.request.UserUpdateRequest;
import spring.demo.my_first_project_spring.dto.response.UserResponse;
import spring.demo.my_first_project_spring.entity.User;
import spring.demo.my_first_project_spring.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")

/**
 * @RequiredArgsConstructor
 * Tự sinh ra constructor cho tất cả các thuộc tính mà có final
 * Bean tự động inject vào constructor đó
 */

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class UserController {
    UserService userService;

    // Thêm User
    @PostMapping
    ApiResponse<UserResponse> create(@RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("Success")
                .result(userService.createUser(request))
                .build();
    }

    // Lấy danh sách User
    @GetMapping
    ApiResponse<List<UserResponse>> getAll() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(
                grantedAuthority -> log.info("GrantedAuthority: {}", grantedAuthority.getAuthority())
        );

        return ApiResponse.<List<UserResponse>>builder()
                .message("Success")
                .result(userService.getUsers())
                .build();
    }

    // Lấy User theo Id
    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getById(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .message("Success")
                .result(userService.getUserById(userId))
                .build();
    }

    @GetMapping("/my-info")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .message("Success")
                .result(userService.getMyInfo())
                .build();
    }

    // Cập nhật User
    @PutMapping("/{userId}")
    ApiResponse<UserResponse> update(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("Success")
                .result(userService.updateUser(userId, request))
                .build();
    }   

    // Xóa User
    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteById(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<String>builder()
                .message("Success")
                .result("User has been deleted!")
                .build();
    }
}
