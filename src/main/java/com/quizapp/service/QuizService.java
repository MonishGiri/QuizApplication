package com.quizapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.quizapp.entity.QuestionWrapper;

public interface QuizService {
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title);

	public List<QuestionWrapper> getQuizQuestions(Integer id);
	
}
