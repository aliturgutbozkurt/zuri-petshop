package com.turkninja.petshop.v1;


import com.turkninja.petshop.api.request.question.CreateQuestionRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.question.CreateQuestionResponse;
import com.turkninja.petshop.api.response.question.GetQuestionResponse;
import com.turkninja.petshop.question.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionResource {
    private final QuestionService questionService;


    public QuestionResource(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateQuestionRequest request){
        CreateQuestionResponse response = questionService.createQuestion(request);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("get-question-by-id")
    public ResponseEntity<GetQuestionResponse> getQuestionById(@RequestParam long questionId){
        GetQuestionResponse response = questionService.GetQuestionById(questionId);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("get-all-questions")
    public ResponseEntity<PageResponse<GetQuestionResponse>> getAllQuestions(@RequestParam int page, @RequestParam int size){
        PageResponse<GetQuestionResponse> allQuestions = questionService.getAllQuestions(page,size);
        return new ResponseEntity<>(allQuestions,HttpStatus.OK);
    }





}
