package com.levyks.ifce.tjw.htmsblog.posts.services.impl;

import com.levyks.ifce.tjw.htmsblog.posts.entities.CategoryEntity;
import com.levyks.ifce.tjw.htmsblog.posts.repositories.CategoryRepository;
import com.levyks.ifce.tjw.htmsblog.posts.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Set<CategoryEntity> splitAndRetrieve(String categoriesString) {
        var names = Arrays.stream(categoriesString.split(","))
                .map(String::trim)
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
