package com.levyks.ifce.tjw.htmsblog.posts.entities;

import com.levyks.ifce.tjw.htmsblog.common.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity(name = "categories")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CategoryEntity extends BaseEntity {
    @ManyToMany(mappedBy = "categories")
    private Set<PostEntity> posts;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String name;

    public CategoryEntity(String name) {
        this.name = name;
    }
}
