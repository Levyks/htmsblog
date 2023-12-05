package com.levyks.ifce.tjw.htmsblog.posts.services;

import com.levyks.ifce.tjw.htmsblog.posts.entities.CategoryEntity;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<CategoryEntity> getMostPopular(Integer limit);

    Set<CategoryEntity> splitAndRetrieve(String categories);
}
