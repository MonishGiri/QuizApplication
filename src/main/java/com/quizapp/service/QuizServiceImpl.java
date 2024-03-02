package com.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapp.entity.Question;
import com.quizapp.entity.QuestionWrapper;
import com.quizapp.entity.Quiz;
import com.quizapp.repository.QuestionRepo;
import com.quizapp.repository.QuizRepo;

@Service
public class QuizServiceImpl implements QuizService{
	
	@Autowired
	QuizRepo quizRepo;
	
	@Autowired
	QuestionRepo questionRepo;
	
	public static Integer createdQuizId;

	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> questions = questionRepo.findRandomQuestionsByCategory(category,numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizRepo.save(quiz);
		createdQuizId = quiz.getId();
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	@Override
	public List<QuestionWrapper> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizRepo.findById(id);
		List<Question> questionsFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		for(Question q : questionsFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getCategory(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getDifficultyLevel());
			questionsForUser.add(qw);
		}
		
		return questionsForUser;
	}
	


}
