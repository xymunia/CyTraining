package v2.teamV2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import v2.teamV2.dto.QuestionDto;
import v2.teamV2.dto.UtilisateurDto;
import v2.teamV2.service.QuestionService;
import v2.teamV2.service.UtilisateurService;

import java.util.List;

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
