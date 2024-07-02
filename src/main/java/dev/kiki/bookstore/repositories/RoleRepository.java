package dev.kiki.bookstore.repositories;

import dev.kiki.bookstore.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleIgnoreCase(String role);

}
