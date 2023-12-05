package com.levyks.ifce.tjw.htmsblog.posts.repositories;

import com.levyks.ifce.tjw.htmsblog.posts.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @EntityGraph(attributePaths = {"postCount"})
    Page<CategoryEntity> findAll(Specification<CategoryEntity> specification, Pageable pageable);

    Set<CategoryEntity> findAllByNameIn(Collection<String> names);
}
