package com.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.repository.QuestionRepo;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	QuestionRepo questionRepo;

	@Override
	public List<String> getQuestionCategories() {
		List<String> categories = questionRepo.getQuestionCategories();
		return categories;
	}

}
