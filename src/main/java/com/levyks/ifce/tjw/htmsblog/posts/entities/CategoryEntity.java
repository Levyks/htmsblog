package com.levyks.ifce.tjw.htmsblog.posts.entities;

import com.levyks.ifce.tjw.htmsblog.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.Set;

@Entity(name = "categories")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CategoryEntity extends BaseEntity {
    @ManyToMany(mappedBy = "categories")
    private Set<PostEntity> posts;

    @Basic(fetch = FetchType.LAZY)
    @Formula("(SELECT COUNT(*) FROM categories_posts cp WHERE cp.category_id = id)")
    private Integer postCount;

    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String name;

    public CategoryEntity(String name) {
        this.name = name;
    }
}
