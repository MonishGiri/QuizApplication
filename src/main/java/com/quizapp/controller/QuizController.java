package com.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.quizapp.entity.QuestionWrapper;
import com.quizapp.service.QuizService;
import com.quizapp.service.QuizServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	
	@GetMapping("user/startQuiz/{category}")
	public String startQuiz(@PathVariable String category, HttpSession session) {
		ResponseEntity<String> response = quizService.createQuiz(category, 10, category+"Quiz");
		if(response.getStatusCode() == HttpStatus.CREATED){
			Integer id = QuizServiceImpl.createdQuizId; 
			System.out.println("Created");
			return "redirect:/user/quiz/get/"+id;
		}
		return "redirect:/user/selectCategory";
	}
	
	@GetMapping("/user/quiz/get/{id}")
	public String getQuizQuestions(@PathVariable Integer id, Model model, HttpSession session){
		List<QuestionWrapper> questionsForUser = quizService.getQuizQuestions(id);
		if(questionsForUser != null) {
			System.out.println("Hello");
			model.addAttribute("questionsForUser", questionsForUser);
			System.out.println(questionsForUser);
			return "quiz";
		}
		System.out.println("I am here");
		return "redirect:/user/selectCategory";
	}
	
	@PostMapping("/submitQuiz")
	public String submitQuiz() {
		System.out.println("Hello submitted..");
		return "submitted";
	}
}
