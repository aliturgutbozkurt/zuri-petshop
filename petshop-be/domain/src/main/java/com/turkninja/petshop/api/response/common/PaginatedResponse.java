package com.turkninja.petshop.api.response.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse {
    private Integer page;
    private Integer size;
    private Long totalCount;
}
