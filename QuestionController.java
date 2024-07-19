package v1.team.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v1.team.dtos.QuestionDto;
import v1.team.dtos.UtilisateurDto;
import v1.team.model.Question;
import v1.team.repositories.QuestionRepository;
import v1.team.repositories.UtilisateurRepository;
import v1.team.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import v1.team.service.UtilisateurService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

//TODO 7/17 : corriger erreur api "Entity must not be null"
@RestController
@RequestMapping("api/questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@Autowired
	public QuestionController(QuestionService questionService){
		this.questionService = questionService;
	}

	/*@Autowired
	UtilisateurService utilisateurService;*/

	@PostMapping
	public ResponseEntity<QuestionDto> newQuestion(@RequestBody QuestionDto request) {
		try {
			QuestionDto savedQuestion = questionService.createQuestion(request);
			return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.print("ERREUR POST METHOD \n \n");
			throw new RuntimeException(e);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<QuestionDto> getQuestionById(@PathVariable("id") int qId) {
		QuestionDto questionDto = questionService.getQuestionById(qId);
		return ResponseEntity.ok(questionDto);
	}


	@GetMapping
	public ResponseEntity<List<QuestionDto>> getAllQuestions() {
		List<QuestionDto> questions = questionService.getAllQuestions();
		return ResponseEntity.ok(questions);
	}


	//In postman : enter data in Body > raw and choose JSON format (in blue)
	//Check JSON format
	@PutMapping("{id}")
	public ResponseEntity<QuestionDto> updateQuestion(@PathVariable("id") int qId, @RequestBody QuestionDto updatedQuestion) {
		QuestionDto questionDto = questionService.updateQuestion(qId, updatedQuestion);
		return ResponseEntity.ok(questionDto);
	}


	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable("id") int qId) {
		questionService.deleteQuestion(qId);
		return ResponseEntity.ok("Question deleted successfully.");
	}

}
