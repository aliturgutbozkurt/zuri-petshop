package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.question.CreateQuestionRequest;
import com.turkninja.petshop.api.response.question.CreateQuestionResponse;
import com.turkninja.petshop.api.response.question.GetQuestionResponse;
import com.turkninja.petshop.entity.question.QuestionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionEntity createRequestDtoToEntity(CreateQuestionRequest request);
    CreateQuestionResponse createRequestEntityToDto(QuestionEntity response);
    GetQuestionResponse GetQuestionEntityToDto(QuestionEntity response);
}
