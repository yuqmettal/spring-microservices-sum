package com.yuqmettal.sum.oauthserver.service;

import java.util.List;
import java.util.stream.Collectors;

import com.yuqmettal.sum.oauthserver.client.UserFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import feign.FeignException;

@Service
public class UserService implements IUserService, UserDetailsService {
	
	@Autowired
	private UserFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			com.yuqmettal.sum.oauthserver.entity.User user = client.findByUsername(username);
			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName()))
					.collect(Collectors.toList());
			
			return new User(
					user.getUsername(),
					user.getPassword(),				
					user.getEnabled(),
					true, true, true,  authorities
					);
		} catch (FeignException e) {
			throw new UsernameNotFoundException("Login Error, User not found");
		}
	}

	@Override
	public com.yuqmettal.sum.oauthserver.entity.User findByUsername(String username) {
		return client.findByUsername(username);
	}

	@Override
	public com.yuqmettal.sum.oauthserver.entity.User update(com.yuqmettal.sum.oauthserver.entity.User usuario, Long id) {
		return client.update(usuario, id);
	}

}