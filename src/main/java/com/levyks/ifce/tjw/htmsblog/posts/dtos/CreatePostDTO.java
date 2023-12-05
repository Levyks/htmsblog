package com.levyks.ifce.tjw.htmsblog.posts.dtos;

import com.levyks.ifce.tjw.htmsblog.posts.entities.CategoryEntity;
import com.levyks.ifce.tjw.htmsblog.posts.entities.PostEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
public final class CreatePostDTO {
    private String title;
    private String content;
    private String categories;

    public static CreatePostDTO from(PostEntity post) {
        var form = new CreatePostDTO();
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());
        form.setCategories(post.getCategories().stream().map(CategoryEntity::getName).collect(Collectors.joining(", ")));
        return form;
    }
}
