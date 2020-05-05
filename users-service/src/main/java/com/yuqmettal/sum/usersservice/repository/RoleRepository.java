package com.yuqmettal.sum.usersservice.repository;

import java.util.List;

import com.yuqmettal.sum.usersservice.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}