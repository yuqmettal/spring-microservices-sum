package com.yuqmettal.sum.usersservice.repository;

import com.yuqmettal.sum.usersservice.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String username);
}