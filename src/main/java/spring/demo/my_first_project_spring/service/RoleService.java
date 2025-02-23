package spring.demo.my_first_project_spring.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.demo.my_first_project_spring.dto.request.RoleRequest;
import spring.demo.my_first_project_spring.dto.response.RoleResponse;
import spring.demo.my_first_project_spring.entity.Role;
import spring.demo.my_first_project_spring.exception.AppException;
import spring.demo.my_first_project_spring.exception.ErrorCode;
import spring.demo.my_first_project_spring.mapper.RoleMapper;
import spring.demo.my_first_project_spring.repository.PermissionRepository;
import spring.demo.my_first_project_spring.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
//        if (roleRepository.existsRoleByName(request.getName())) {
//            throw new AppException(ErrorCode.ROLE_EXISTED);
//        }

        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());

        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
