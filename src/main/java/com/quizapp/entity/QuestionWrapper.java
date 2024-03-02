package com.quizapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionWrapper {
	private Integer id;
	private String category;
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String difficultyLevel;
}
