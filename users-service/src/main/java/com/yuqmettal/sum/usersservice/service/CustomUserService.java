package com.yuqmettal.sum.usersservice.service;

import java.util.List;

import com.yuqmettal.sum.usersservice.entity.Role;
import com.yuqmettal.sum.usersservice.entity.User;
import com.yuqmettal.sum.usersservice.repository.RoleRepository;
import com.yuqmettal.sum.usersservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Override
    public User signUp(User user) {
        User dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser != null) {
            throw new RuntimeException("User already exist.");
        }
        List<Role> userRoles = roleRepository.findByName("ROLE_USER");
        user.setRoles(userRoles);
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
    
}