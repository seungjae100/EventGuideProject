package com.web.eventguideproject.publicapi;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginatedResponse<T> {

    private List<T> items;
    private int totalPages;

    public PaginatedResponse(List<T> items, int totalPages) {
        this.items = items;
        this.totalPages = totalPages;
    }
}