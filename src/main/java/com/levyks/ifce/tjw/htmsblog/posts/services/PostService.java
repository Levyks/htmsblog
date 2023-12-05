package com.levyks.ifce.tjw.htmsblog.posts.services;

import com.levyks.ifce.tjw.htmsblog.common.dtos.PageRequestDTO;
import com.levyks.ifce.tjw.htmsblog.posts.dtos.CreatePostDTO;
import com.levyks.ifce.tjw.htmsblog.posts.entities.PostEntity;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.Optional;

public interface PostService {
    Page<PostEntity> getPage(PageRequestDTO request);

    void create(CreatePostDTO form);

    void update(PostEntity post, CreatePostDTO form);

    void delete(PostEntity post);

    Optional<PostEntity> findByIdOrSlug(String idOrSlug);

    Optional<PostEntity> findById(Long id);

    boolean canEdit(PostEntity post, Principal principal);
}
