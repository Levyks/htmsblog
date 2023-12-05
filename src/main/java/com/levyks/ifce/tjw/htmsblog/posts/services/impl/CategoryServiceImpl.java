package com.levyks.ifce.tjw.htmsblog.posts.services.impl;

import com.levyks.ifce.tjw.htmsblog.common.utils.SlugUtils;
import com.levyks.ifce.tjw.htmsblog.posts.entities.CategoryEntity;
import com.levyks.ifce.tjw.htmsblog.posts.repositories.CategoryRepository;
import com.levyks.ifce.tjw.htmsblog.posts.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public List<CategoryEntity> getMostPopular(Integer limit) {
        var sort = Sort.by(Sort.Direction.DESC, "postCount");
        var pageable = PageRequest.of(0, limit, sort);
        var page = categoryRepository.findAll((Specification<CategoryEntity>) null, pageable);
        return page.getContent();
    }

    @Override
    public Set<CategoryEntity> splitAndRetrieve(String categoriesString) {
        var names = Arrays.stream(categoriesString.split(","))
                .map(String::trim)
                .map(SlugUtils::generateSlug)
                .filter(s -> !s.isBlank())
                .toList();

        var existentCategories = categoryRepository.findAllByNameIn(names);

        var categories = new HashSet<>(existentCategories);

        for (var name : names) {
            categories.add(new CategoryEntity(name));
        }

        return categories;
    }
}
