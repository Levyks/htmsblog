package com.levyks.ifce.tjw.htmsblog.posts.controllers;

import com.levyks.ifce.tjw.htmsblog.common.dtos.PageRequestDTO;
import com.levyks.ifce.tjw.htmsblog.posts.dtos.CreatePostDTO;
import com.levyks.ifce.tjw.htmsblog.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public String index(Model model, PageRequestDTO request) {
        var page = postService.getPage(request);
        model.addAttribute("posts", page);
        return "posts/index";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String create(Model model) {
        model.addAttribute("form", new CreatePostDTO());
        return "posts/create";
    }

    @GetMapping("/{idOrSlug}")
    public String show(Model model, @PathVariable String idOrSlug) {
        return postService.findByIdOrSlug(idOrSlug)
                .map(post -> {
                    model.addAttribute("post", post);
                    return "posts/show";
                })
                .orElse("redirect:/posts");
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String store(CreatePostDTO form) {
        postService.create(form);
        return "redirect:/posts";
    }
}
