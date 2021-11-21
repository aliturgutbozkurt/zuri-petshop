package com.turkninja.petshop.v1;


import com.turkninja.petshop.answer.AnswerService;
import com.turkninja.petshop.api.request.answer.CreateAnswerRequest;

import com.turkninja.petshop.api.response.answer.GetAllAnswerResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answer")
@RequiredArgsConstructor
public class AnswerResource {
    public final AnswerService answerService;

    @PostMapping
    public ResponseEntity create(@RequestBody CreateAnswerRequest answer) {

        return new ResponseEntity(answerService.createAnswer(answer), HttpStatus.CREATED);
    }
    @GetMapping("get-all-response-by-question-id")
    public ResponseEntity<List<GetAllAnswerResponse>> getAllAnswerByQuestionId(@RequestParam long questionId){
        List<GetAllAnswerResponse> answerResponses = answerService.getAllAnswerByQuestionId(questionId);
        return new ResponseEntity(answerResponses,HttpStatus.OK);
    }

}
