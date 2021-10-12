package com.turkninja.petshop.question.impl;

import com.turkninja.petshop.QuestionRepository;
import com.turkninja.petshop.api.request.question.CreateQuestionRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.question.CreateQuestionResponse;
import com.turkninja.petshop.api.response.question.GetQuestionResponse;
import com.turkninja.petshop.entity.answer.AnswerEntity;
import com.turkninja.petshop.entity.question.QuestionEntity;
import com.turkninja.petshop.mapper.QuestionMapper;
import com.turkninja.petshop.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;

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

    @Override
    public GetQuestionResponse GetQuestionById(long questionId) {
        QuestionEntity result = questionRepository.getOne(questionId);
        GetQuestionResponse resultDto = questionMapper.GetQuestionEntityToDto(result);
        return resultDto;
    }

    @Override
    public PageResponse<GetQuestionResponse> getAllQuestions(int page, int size) {
        Page<QuestionEntity> allQuestions = questionRepository.findAll(PageRequest.of(page,size,Sort.by("createdAt")));
        return questionMapper.pageEntitiesToGetPageResponse(allQuestions);
    }

}
