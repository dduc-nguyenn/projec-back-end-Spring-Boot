package spring.demo.my_first_project_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.demo.my_first_project_spring.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

}
