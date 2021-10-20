package com.turkninja.petshop.v1;


import com.turkninja.petshop.answer.AnswerService;
import com.turkninja.petshop.api.request.answer.CreateAnswerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/answer")
@RequiredArgsConstructor
public class AnswerResource {
    public final AnswerService answerService;

    @PostMapping
    public ResponseEntity create(@RequestBody CreateAnswerRequest answer) {

        return new ResponseEntity(answerService.createAnswer(answer), HttpStatus.CREATED);
    }
}
