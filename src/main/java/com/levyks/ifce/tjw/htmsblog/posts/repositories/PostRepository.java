package com.levyks.ifce.tjw.htmsblog.posts.repositories;

import com.levyks.ifce.tjw.htmsblog.posts.entities.PostEntity;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @EntityGraph(attributePaths = {"preview", "createdBy"})
    Page<PostEntity> findAll(Specification<PostEntity> specification, Pageable pageable);

    @EntityGraph(attributePaths = {"content", "createdBy", "updatedBy", "categories"})
    Optional<PostEntity> findBySlug(String slug);

    @NonNull
    @EntityGraph(attributePaths = {"content", "createdBy", "updatedBy", "categories"})
    Optional<PostEntity> findById(@NonNull Long id);

    List<PostEntity> findAllBySlugStartsWith(String slug);

    class Specifications {

        public static Specification<PostEntity> withTitleLike(String title) {
            return (root, query, builder) -> builder.like(root.get("title"), "%" + title + "%");
        }

        public static Specification<PostEntity> withSlugLike(String slug) {
            return (root, query, builder) -> builder.like(root.get("slug"), "%" + slug + "%");
        }

        public static Specification<PostEntity> withCategoryNameLike(String categoryName) {
            return (root, query, builder) -> builder.like(root.join("categories", JoinType.LEFT).get("name"), "%" + categoryName + "%");
        }
    }
}
