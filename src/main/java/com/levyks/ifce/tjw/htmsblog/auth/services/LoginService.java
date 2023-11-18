package com.levyks.ifce.tjw.htmsblog.auth.services;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

public interface LoginService {
    @Nullable
    String getErrorMessage(HttpServletRequest request, Locale locale);
}
