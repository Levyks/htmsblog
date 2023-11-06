package com.levyks.ifce.tjw.htmsblog.common.entities;

import com.levyks.ifce.tjw.htmsblog.auth.details.UserDetailsImpl;
import com.levyks.ifce.tjw.htmsblog.users.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    private Long createdById;

    @JoinColumn(name = "createdById", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private UserEntity createdBy;

    private Long updatedById;

    @JoinColumn(name = "updatedById", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private UserEntity updatedBy;

    @PreUpdate
    public void preUpdate() {
        this.updatedById = getCurrentUserId();
    }

    @PrePersist
    public void prePersist() {
        this.createdById = getCurrentUserId();
    }

    private @Nullable Long getCurrentUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {
            return userDetails.getId();
        }
        return null;
    }
}
