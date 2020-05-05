package com.yuqmettal.sum.oauthserver.client;

import com.yuqmettal.sum.oauthserver.entity.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "users-service")
public interface UserFeignClient {
	
	@GetMapping("/users/search/find-by-username")
	public User findByUsername(@RequestParam String username);
	
	@PutMapping("/users/{id}")
	public User update(@RequestBody User user, @PathVariable Long id);

}