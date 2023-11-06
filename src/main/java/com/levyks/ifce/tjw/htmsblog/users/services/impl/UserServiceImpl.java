package com.levyks.ifce.tjw.htmsblog.users.services.impl;

import com.levyks.ifce.tjw.htmsblog.users.entities.UserEntity;
import com.levyks.ifce.tjw.htmsblog.users.repositories.UserRepository;
import com.levyks.ifce.tjw.htmsblog.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
