package spring.demo.my_first_project_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.demo.my_first_project_spring.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    boolean existsRoleByName(String name);
}
