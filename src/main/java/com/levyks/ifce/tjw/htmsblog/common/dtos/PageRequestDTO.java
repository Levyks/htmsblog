package com.levyks.ifce.tjw.htmsblog.common.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class PageRequestDTO {
    private int page = 1;
    private int size = 20;
    private Collection<String> sort = Collections.emptyList();
    private Sort.Direction direction = Sort.Direction.ASC;
    private String search;

    public Pageable toPageable() {
        var sort = Sort.by(this.sort.stream().map(property -> new Sort.Order(direction, property)).toList());
        return PageRequest.of(page - 1, size, sort);
    }

}
