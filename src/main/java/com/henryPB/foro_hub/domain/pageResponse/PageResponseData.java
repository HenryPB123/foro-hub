package com.henryPB.foro_hub.domain.pageResponse;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponseData<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
    public PageResponseData(Page<T> page){
        this(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
