package com.levyks.ifce.tjw.htmsblog.posts.services.impl;

import com.levyks.ifce.tjw.htmsblog.auth.details.UserDetailsImpl;
import com.levyks.ifce.tjw.htmsblog.common.dtos.PageRequestDTO;
import com.levyks.ifce.tjw.htmsblog.common.utils.SlugUtils;
import com.levyks.ifce.tjw.htmsblog.posts.dtos.CreatePostDTO;
import com.levyks.ifce.tjw.htmsblog.posts.entities.PostEntity;
import com.levyks.ifce.tjw.htmsblog.posts.repositories.PostRepository;
import com.levyks.ifce.tjw.htmsblog.posts.services.CategoryService;
import com.levyks.ifce.tjw.htmsblog.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final CategoryService categoryService;
    private final PostRepository postRepository;

    @Override
    public Page<PostEntity> getPage(PageRequestDTO request) {
        return postRepository.findAll(getSpecification(request), request.toPageable());
    }

    @Override
    @Transactional
    public void create(CreatePostDTO form) {
        update(new PostEntity(), form);
    }

    @Override
    @Transactional
    public void update(PostEntity post, CreatePostDTO form) {
        var categories = categoryService.splitAndRetrieve(form.getCategories());
        var slug = SlugUtils.generateUniqueSlug(
                postRepository::findAllBySlugStartsWith,
                PostEntity::getSlug,
                form.getTitle(),
                List.of("create")
        );

        post.setTitle(form.getTitle());
        post.setCategories(categories);
        post.setContent(form.getContent());
        post.setSlug(slug);

        postRepository.save(post);
    }

    @Override
    public void delete(PostEntity post) {
        postRepository.delete(post);
    }

    @Override
    public Optional<PostEntity> findByIdOrSlug(String idOrSlug) {
        if (idOrSlug.matches("\\d+")) {
            var post = postRepository.findById(Long.parseLong(idOrSlug));
            if (post.isPresent()) return post;
        }
        return postRepository.findBySlug(idOrSlug);
    }

    @Override
    public Optional<PostEntity> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public boolean canEdit(PostEntity post, Principal principal) {
        if (principal == null) return false;
        var details = (UserDetailsImpl) ((Authentication) principal).getPrincipal();
        return details.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) ||
                post.getCreatedBy().getId().equals(details.getId());
    }

    private Specification<PostEntity> getSpecification(PageRequestDTO specification) {
        var spec = Specification.<PostEntity>where(null);

        var search = specification.getSearch();
        if (search != null && !search.isBlank()) {
            spec = spec.and(PostRepository.Specifications.withTitleLike(search)
                    .or(PostRepository.Specifications.withSlugLike(search))
                    .or(PostRepository.Specifications.withCategoryNameLike(search)));
        }

        var category = specification.getCategory();
        if (category != null && !category.isBlank()) {
            spec = spec.and(PostRepository.Specifications.withCategoryName(category));
        }

        return spec;
    }
}
