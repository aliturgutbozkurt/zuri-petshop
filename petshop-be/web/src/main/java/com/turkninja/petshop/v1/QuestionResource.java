package com.turkninja.petshop.v1;


import com.turkninja.petshop.api.request.question.CreateQuestionRequest;
import com.turkninja.petshop.api.response.question.CreateQuestionResponse;
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




}
