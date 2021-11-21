package com.turkninja.petshop.answer.impl;

import com.turkninja.petshop.AnswerRepository;
import com.turkninja.petshop.QuestionRepository;
import com.turkninja.petshop.answer.AnswerService;
import com.turkninja.petshop.api.request.answer.CreateAnswerRequest;
import com.turkninja.petshop.api.response.answer.CreateAnswerResponse;
import com.turkninja.petshop.api.response.answer.GetAllAnswerResponse;
import com.turkninja.petshop.entity.answer.AnswerEntity;
import com.turkninja.petshop.entity.question.QuestionEntity;
import com.turkninja.petshop.mapper.AnswerMapper;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AnswerMapper mapper;


    public AnswerServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository, AnswerMapper mapper) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.mapper = mapper;
    }


    @Override
    public CreateAnswerResponse createAnswer(CreateAnswerRequest answer)  {
        QuestionEntity question = questionRepository.getOne(answer.getQuestionId());
        AnswerEntity lastAnswer = answerRepository.findTopAnswerByQuestionId(answer.getQuestionId());
        AnswerEntity newAnswer = mapper.createAnswerDtoToEntity(answer);
        newAnswer.setQuestion(question);
        if(lastAnswer != null){
            newAnswer.setParentAnswerId(lastAnswer.getId());
        }
        AnswerEntity addedAnswer = answerRepository.save(newAnswer);
        question.setLastAnswerId(addedAnswer.getId());
        questionRepository.save(question);
        return mapper.createAnswerEntityToDto(addedAnswer);

    }

    @Override
    public List<GetAllAnswerResponse> getAllAnswerByQuestionId(long questionId) {
        List<AnswerEntity> allAnswers = answerRepository.findAllByQuestionId(questionId);
        return mapper.listEntitesToGetListResponse(answerRepository.findAllByQuestionId(questionId));
    }
}
