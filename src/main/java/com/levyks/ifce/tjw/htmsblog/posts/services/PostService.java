package com.levyks.ifce.tjw.htmsblog.posts.services;

import com.levyks.ifce.tjw.htmsblog.common.dtos.PageRequestDTO;
import com.levyks.ifce.tjw.htmsblog.posts.dtos.CreatePostDTO;
import com.levyks.ifce.tjw.htmsblog.posts.entities.PostEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostService {
    Page<PostEntity> getPage(PageRequestDTO request);

    void create(CreatePostDTO form);

    Optional<PostEntity> findByIdOrSlug(String idOrSlug);
}
