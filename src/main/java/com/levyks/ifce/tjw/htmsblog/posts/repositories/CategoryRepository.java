package com.levyks.ifce.tjw.htmsblog.posts.repositories;

import com.levyks.ifce.tjw.htmsblog.posts.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Set<CategoryEntity> findAllByNameIn(Collection<String> names);
}
