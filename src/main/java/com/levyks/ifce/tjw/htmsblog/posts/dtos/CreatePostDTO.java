package com.levyks.ifce.tjw.htmsblog.posts.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public final class CreatePostDTO {
    private String title;
    private String content;
    private MultipartFile image;
    private String categories;
}
