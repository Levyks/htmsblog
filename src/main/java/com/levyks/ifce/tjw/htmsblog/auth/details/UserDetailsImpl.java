package com.levyks.ifce.tjw.htmsblog.auth.details;

import com.levyks.ifce.tjw.htmsblog.users.entities.UserEntity;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String name;
    private final String username;
    private final String password;
    private final Collection<SimpleGrantedAuthority> authorities;
    private final boolean enabled;

    public UserDetailsImpl(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.authorities = generateAuthorities(user);
    }

    private Collection<SimpleGrantedAuthority> generateAuthorities(UserEntity user) {
        if (user.isAdmin()) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return Collections.emptyList();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
