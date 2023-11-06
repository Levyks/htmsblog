package com.levyks.ifce.tjw.htmsblog.users.services;

import com.levyks.ifce.tjw.htmsblog.users.entities.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByEmail(String email);
}
