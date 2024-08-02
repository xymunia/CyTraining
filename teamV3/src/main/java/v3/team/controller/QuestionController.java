package v3.team.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v3.team.dtos.QuestionDto;
import v3.team.service.Interfaces.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@CrossOrigin
@RestController
@RequestMapping(path = "questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@Autowired
	public QuestionController(QuestionService questionService){
		this.questionService = questionService;
	}
	

	@PostMapping(path = "/new", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<QuestionDto> newQuestion(@RequestBody QuestionDto request) {
		try {
			QuestionDto savedQuestion = questionService.createQuestion(request);
			return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.print("ERREUR POST METHOD \n \n");
			throw new RuntimeException(e);
		}
	}


	@GetMapping(path = "/infos/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<QuestionDto> getQuestionById(@PathVariable("id") int qId) {
		QuestionDto questionDto = questionService.getQuestionById(qId);
		return ResponseEntity.ok(questionDto);
	}


	@GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<QuestionDto>> getAllQuestions() {
		List<QuestionDto> questions = questionService.getAllQuestions();
		return ResponseEntity.ok(questions);
	}


	@PatchMapping(path ="/modifier_question/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<QuestionDto> updateQuestion(@PathVariable("id") int qId, @RequestBody QuestionDto updatedQuestion) {
		QuestionDto questionDto = questionService.updateQuestion(qId, updatedQuestion);
		return ResponseEntity.ok(questionDto);
	}


	@PatchMapping(path = "/valider_question/{id_q}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> acceptQuestion(@PathVariable("id_q") int qId) {
		questionService.validerQuestion(qId);
		return ResponseEntity.ok("Question validée ? Vérifiez avec un Get");
	}


	@PatchMapping(path = "/refuser_question/{id_q}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> refusQuestion(@PathVariable("id_q") int qId) {
		questionService.refusValidation(qId);
		return ResponseEntity.ok("Question refusée ? Vérifiez avec un Get");
	}


	@PatchMapping(path = "/remise_attente/{id_q}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<QuestionDto> remiseAttente(@PathVariable("id_q") int qId) {
		QuestionDto questionAtt = questionService.remiseAttente(qId);
		return ResponseEntity.ok(questionAtt);
	}


	@DeleteMapping(path ="/supprimer/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable("id") int qId) {
		questionService.deleteQuestion(qId);
		return ResponseEntity.ok("Vérifiez la suppression avec un Get ou dans le CLI");
	}

}
