package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
