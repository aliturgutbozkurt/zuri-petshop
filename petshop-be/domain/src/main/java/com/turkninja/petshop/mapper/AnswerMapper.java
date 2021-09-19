package com.turkninja.petshop.mapper;


import com.turkninja.petshop.api.request.answer.CreateAnswerRequest;
import com.turkninja.petshop.api.response.answer.CreateAnswerResponse;
import com.turkninja.petshop.entity.answer.AnswerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerEntity createAnswerDtoToEntity(CreateAnswerRequest request);
    CreateAnswerResponse createAnswerEntityToDto(AnswerEntity response);
}
