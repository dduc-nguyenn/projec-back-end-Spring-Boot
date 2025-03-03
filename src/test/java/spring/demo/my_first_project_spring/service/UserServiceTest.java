package spring.demo.my_first_project_spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import spring.demo.my_first_project_spring.constant.PredefinedRole;
import spring.demo.my_first_project_spring.dto.request.UserCreateRequest;
import spring.demo.my_first_project_spring.dto.response.UserResponse;
import spring.demo.my_first_project_spring.entity.Role;
import spring.demo.my_first_project_spring.entity.User;
import spring.demo.my_first_project_spring.exception.AppException;
import spring.demo.my_first_project_spring.repository.RoleRepository;
import spring.demo.my_first_project_spring.repository.UserRepository;

import java.time.LocalDate;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    private UserCreateRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(2004, 1, 16);

        request = UserCreateRequest.builder()
                .username("ducnguyen")
                .firstName("duc")
                .lastName("nguyen")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("671f392f")
                .username("ducnguyen")
                .firstName("duc")
                .lastName("nguyen")
                .dob(dob)
                .build();

        user = user.builder()
                .id("671f392f")
                .username("ducnguyen")
                .firstName("duc")
                .lastName("nguyen")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);

        // THEN
        Assertions.assertThat(response.getId()).isEqualTo("671f392f");
        Assertions.assertThat(response.getUsername()).isEqualTo("ducnguyen");
    }

    @Test
    void createUser_userExisted_fail() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN
        var exception = assertThrows(AppException.class,
                () -> userService.createUser(request));

        Assertions.assertThat(exception.getErrorCode().getCode())
                .isEqualTo(1001);
    }
}
