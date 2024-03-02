package com.quizapp.service;

import com.quizapp.entity.User;

public interface UserService {

	public User saveUser(User user);

	public void removeSessionMessage();

}
