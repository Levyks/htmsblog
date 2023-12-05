package com.levyks.ifce.tjw.htmsblog.posts.entities;

import com.levyks.ifce.tjw.htmsblog.common.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "comments")
@NoArgsConstructor
@Getter
@Setter
public class CommentEntity extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}
