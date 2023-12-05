package com.levyks.ifce.tjw.htmsblog.auth.services.impl;

import com.levyks.ifce.tjw.htmsblog.auth.services.LoginService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MessageSource messageSource;

    @Nullable
    @Override
    public String getErrorMessage(HttpServletRequest request, Locale locale) {
        if (request.getParameter("error") == null) return null;

        var session = request.getSession();
        if (session != null && session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) instanceof AuthenticationException ex) {
            return ex.getMessage();
        }

        return messageSource.getMessage("error.unknown", null, locale);
    }
}
