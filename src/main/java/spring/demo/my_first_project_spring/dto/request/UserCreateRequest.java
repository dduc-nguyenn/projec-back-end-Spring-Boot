package spring.demo.my_first_project_spring.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import spring.demo.my_first_project_spring.validator.DobConstraint;

import java.time.LocalDate;

@Data
@Builder // Để tạo đối tượng nhanh hơn
@NoArgsConstructor // Tự sinh ra constructor không tham số
@AllArgsConstructor // Tự sinh ra constructor với tất cả tham số
@FieldDefaults(level = AccessLevel.PRIVATE) // Để xác định các thuộc tính theo 1 kiểu Access

public class UserCreateRequest {
    @Size(min = 5, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    @NotEmpty()
    String firstName;

    @NotEmpty()
    String lastName;

    @DobConstraint(min = 16, message = "INVALID_DOB")
    LocalDate dob;
}
