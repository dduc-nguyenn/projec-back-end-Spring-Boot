package spring.demo.my_first_project_spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import spring.demo.my_first_project_spring.dto.request.PermissionRequest;
import spring.demo.my_first_project_spring.dto.request.UserCreateRequest;
import spring.demo.my_first_project_spring.dto.request.UserUpdateRequest;
import spring.demo.my_first_project_spring.dto.response.PermissionResponse;
import spring.demo.my_first_project_spring.dto.response.UserResponse;
import spring.demo.my_first_project_spring.entity.Permission;
import spring.demo.my_first_project_spring.entity.User;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
