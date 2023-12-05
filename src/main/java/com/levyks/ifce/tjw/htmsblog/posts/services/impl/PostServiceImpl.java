package com.levyks.ifce.tjw.htmsblog.posts.services.impl;

import com.levyks.ifce.tjw.htmsblog.common.dtos.PageRequestDTO;
import com.levyks.ifce.tjw.htmsblog.posts.dtos.CreatePostDTO;
import com.levyks.ifce.tjw.htmsblog.posts.entities.PostEntity;
import com.levyks.ifce.tjw.htmsblog.posts.repositories.PostRepository;
import com.levyks.ifce.tjw.htmsblog.posts.services.CategoryService;
import com.levyks.ifce.tjw.htmsblog.posts.services.PostService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final CategoryService categoryService;
    private final PostRepository postRepository;

    @Override
    public Page<PostEntity> getPage(PageRequestDTO request) {
        var spec = getSpecification(request.getSearch());
        return postRepository.findAll(spec, request.toPageable());
    }

    private String generateSlug(String title) {
        var slug = Normalizer.normalize(title, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();

        var existentSlugs = postRepository.findAllBySlugStartsWith(slug)
                .stream()
                .map(PostEntity::getSlug)
                .collect(Collectors.toSet());

        if (existentSlugs.contains(slug)) {
            var i = 1;
            while (existentSlugs.contains(slug + "-" + i)) i++;
            return slug + "-" + i;
        }

        return slug;
    }

    @Override
    @Transactional
    public void create(CreatePostDTO form) {
        var post = new PostEntity();
        post.setTitle(form.getTitle());
        post.setCategories(categoryService.splitAndRetrieve(form.getCategories()));
        post.setContent(form.getContent());
        post.setSlug(generateSlug(form.getTitle()));
        postRepository.save(post);
    }

    @Override
    public Optional<PostEntity> findByIdOrSlug(String idOrSlug) {
        if (idOrSlug.matches("\\d+")) {
            var post = postRepository.findById(Long.parseLong(idOrSlug));
            if (post.isPresent()) return post;
        }
        return postRepository.findBySlug(idOrSlug);
    }

    private Specification<PostEntity> getSpecification(@Nullable String search) {
        if (search == null || search.isBlank()) {
            return null;
        }

        return Specification.where(
                PostRepository.Specifications.withTitleLike(search)
                        .or(PostRepository.Specifications.withSlugLike(search))
                        .or(PostRepository.Specifications.withCategoryNameLike(search))
        );
    }
}
