package com.levyks.ifce.tjw.htmsblog.posts.entities;

import com.levyks.ifce.tjw.htmsblog.common.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "posts")
@NoArgsConstructor
@Getter
@Setter
public class PostEntity extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, unique = true)
    private String slug;
}
