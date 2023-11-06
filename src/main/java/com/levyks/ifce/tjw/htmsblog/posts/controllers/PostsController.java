package com.levyks.ifce.tjw.htmsblog.posts.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostsController {

    @RequestMapping
    public String index() {
        return "posts/index";
    }

    @RequestMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String create() {
        return "posts/create";
    }
}
