package com.levyks.ifce.tjw.htmsblog.posts.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class CreatePostDTO {
    private String title;
    private String content;
    private String categories;
}
