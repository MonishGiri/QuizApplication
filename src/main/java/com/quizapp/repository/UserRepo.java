package com.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByEmail(String emaill);

}
