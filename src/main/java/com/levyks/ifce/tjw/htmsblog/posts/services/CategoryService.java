package com.levyks.ifce.tjw.htmsblog.posts.services;

import com.levyks.ifce.tjw.htmsblog.posts.entities.CategoryEntity;

import java.util.Set;

public interface CategoryService {
    Set<CategoryEntity> splitAndRetrieve(String categories);
}
