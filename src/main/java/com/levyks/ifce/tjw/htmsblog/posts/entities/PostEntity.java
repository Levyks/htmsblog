package com.levyks.ifce.tjw.htmsblog.posts.entities;

import com.levyks.ifce.tjw.htmsblog.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "posts")
@NoArgsConstructor
@Getter
@Setter
public class PostEntity extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Basic(fetch = FetchType.LAZY, optional = false)
    private String content;

    @Basic(fetch = FetchType.LAZY)
    @Formula("SUBSTRING(content, 1, 100)")
    private String preview;

    @Column(nullable = false, unique = true)
    private String slug;

    @OneToMany(mappedBy = "post")
    private Set<CommentEntity> comments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "categories_posts",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories;

    public String getCategoriesString() {
        return categories.stream().map(CategoryEntity::getName).collect(Collectors.joining(", "));
    }
}
