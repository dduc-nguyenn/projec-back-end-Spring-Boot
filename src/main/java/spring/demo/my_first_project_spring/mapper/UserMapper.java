package spring.demo.my_first_project_spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.demo.my_first_project_spring.dto.request.UserCreateRequest;
import spring.demo.my_first_project_spring.dto.request.UserUpdateRequest;
import spring.demo.my_first_project_spring.dto.response.UserResponse;
import spring.demo.my_first_project_spring.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true) // trường roles sẽ null khi response về
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
