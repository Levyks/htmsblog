package com.levyks.ifce.tjw.htmsblog.auth.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(Principal principal, HttpServletRequest request) {
        var session = request.getSession();

        String errorMessage = null;
        if (session != null && session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) instanceof AuthenticationException ex) {
            errorMessage = ex.getMessage();
        }

        System.out.println("Login error message: " + errorMessage);
        return "auth/login";
    }

}
