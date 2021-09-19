package com.turkninja.petshop.question.impl;

import com.turkninja.petshop.QuestionRepository;
import com.turkninja.petshop.api.request.question.CreateQuestionRequest;
import com.turkninja.petshop.api.response.question.CreateQuestionResponse;
import com.turkninja.petshop.entity.answer.AnswerEntity;
import com.turkninja.petshop.entity.question.QuestionEntity;
import com.turkninja.petshop.mapper.QuestionMapper;
import com.turkninja.petshop.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public CreateQuestionResponse createQuestion(CreateQuestionRequest request) {
        QuestionEntity entity = questionMapper.createRequestDtoToEntity(request);
        QuestionEntity response = questionRepository.save(entity);
        return questionMapper.createRequestEntityToDto(response);
    }

}
