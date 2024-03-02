package com.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quizapp.entity.Question;

public interface QuestionRepo extends JpaRepository<Question, Integer>{
	
	@Query("select distinct(q.category) q from Question q")
	List<String> getQuestionCategories();

	@Query(value = "SELECT TOP (:numQ) * FROM Question q where q.category = :category ORDER BY NEWID()", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
