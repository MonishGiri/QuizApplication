package com.quizapp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.quizapp.entity.User;
import com.quizapp.repository.UserRepo;
import com.quizapp.service.QuestionService;
import com.quizapp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@ControllerAdvice
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	QuestionService questionService;
	
	public static User myUser;

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}

	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/quiz")
	public String quiz() {
		return "quiz";
	}

	@GetMapping("/user/profile")
	public String profile(Principal p, Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return "profile";
	}

	@GetMapping("/user/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("user/selectCategory")
	public String selectCategory(Model model, HttpSession session) {
		List<String> categories = questionService.getQuestionCategories();
		model.addAttribute("categoriesList", categories);
		return "selectCategory";
	}
	
	

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session, Model m) {

		// System.out.println(user);

		User u = userService.saveUser(user);

		if (u != null) {
			// System.out.println("save sucess");
			session.setAttribute("msg", "Register successfully");

		} else {
			// System.out.println("error in server");
			session.setAttribute("msg", "Something wrong server");
		}
		return "redirect:/register";
	}
	

}
