package com.yuqmettal.sum.usersservice.repository;

import com.yuqmettal.sum.usersservice.entity.User;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	@RestResource(path = "find-by-username")
	public User findByUsername(@Param("username") String username);

}