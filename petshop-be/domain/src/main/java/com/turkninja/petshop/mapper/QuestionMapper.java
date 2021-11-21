package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.question.CreateQuestionRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.question.CreateQuestionResponse;
import com.turkninja.petshop.api.response.question.GetQuestionResponse;
import com.turkninja.petshop.entity.question.QuestionEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionEntity createRequestDtoToEntity(CreateQuestionRequest request);
    CreateQuestionResponse createRequestEntityToDto(QuestionEntity response);
    GetQuestionResponse GetQuestionEntityToDto(QuestionEntity response);


    default PageResponse<GetQuestionResponse>  pageEntitiesToGetPageResponse(Page<QuestionEntity> entities){
        PageResponse<GetQuestionResponse> response = new PageResponse<GetQuestionResponse>();
        List<QuestionEntity> content = entities.getContent();
        response.setContent(content.stream().map(ce->this.GetQuestionEntityToDto(ce)).collect(Collectors.toList()));
        response.setTotalPages(entities.getTotalPages());
        response.setTotalElements(entities.getTotalElements());
        return response;
    }
}
