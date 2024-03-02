package com.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.entity.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer>{

}
