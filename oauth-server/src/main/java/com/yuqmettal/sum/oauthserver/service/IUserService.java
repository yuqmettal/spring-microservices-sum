package com.yuqmettal.sum.oauthserver.service;

import com.yuqmettal.sum.oauthserver.entity.User;

public interface IUserService {
    public User findByUsername(String username);
	
	public User update(User usuario, Long id);
}