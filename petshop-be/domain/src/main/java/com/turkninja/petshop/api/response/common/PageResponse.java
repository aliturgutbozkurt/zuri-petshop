package com.turkninja.petshop.api.response.common;

import lombok.Data;

import java.util.List;

/**
 * @author ali turgut bozkurt
 * Created at 7/31/2021
 */

@Data
public class PageResponse<T> {

    List<T> content;
    int totalPages;
    long totalElements;
}
