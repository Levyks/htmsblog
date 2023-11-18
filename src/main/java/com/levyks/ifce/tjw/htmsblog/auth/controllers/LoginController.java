package com.levyks.ifce.tjw.htmsblog.auth.controllers;

import com.levyks.ifce.tjw.htmsblog.auth.services.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public String login(HttpServletRequest request, Model model) {
        var errorMessage = loginService.getErrorMessage(request, LocaleContextHolder.getLocale());
        model.addAttribute("errorMessage", errorMessage);

        return "auth/login";
    }


}
