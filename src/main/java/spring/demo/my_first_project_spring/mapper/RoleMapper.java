package spring.demo.my_first_project_spring.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import spring.demo.my_first_project_spring.dto.request.RoleRequest;
import spring.demo.my_first_project_spring.dto.response.RoleResponse;
import spring.demo.my_first_project_spring.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)

    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
