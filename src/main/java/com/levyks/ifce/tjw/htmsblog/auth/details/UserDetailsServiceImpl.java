package com.levyks.ifce.tjw.htmsblog.auth.details;

import com.levyks.ifce.tjw.htmsblog.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return new UserDetailsImpl(user);
    }
}
