package com.turkninja.petshop.mapper;


import com.turkninja.petshop.api.request.answer.CreateAnswerRequest;
import com.turkninja.petshop.api.response.answer.CreateAnswerResponse;
import com.turkninja.petshop.api.response.answer.GetAllAnswerResponse;
import com.turkninja.petshop.api.response.product.GetCategoryResponse;
import com.turkninja.petshop.entity.answer.AnswerEntity;
import com.turkninja.petshop.entity.product.ProductCategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerEntity createAnswerDtoToEntity(CreateAnswerRequest request);
    CreateAnswerResponse createAnswerEntityToDto(AnswerEntity response);
    GetAllAnswerResponse getAllAnswersEntityToDto(AnswerEntity responses);

    default List<GetAllAnswerResponse> listEntitesToGetListResponse (List<AnswerEntity> entities){
        return entities.stream().map(ce->this.getAllAnswersEntityToDto(ce)).collect(Collectors.toList());
    }
}
