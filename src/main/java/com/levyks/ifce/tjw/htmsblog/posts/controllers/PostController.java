package com.levyks.ifce.tjw.htmsblog.posts.controllers;

import com.levyks.ifce.tjw.htmsblog.common.dtos.PageRequestDTO;
import com.levyks.ifce.tjw.htmsblog.common.enums.FormAction;
import com.levyks.ifce.tjw.htmsblog.common.utils.HtmxUtils;
import com.levyks.ifce.tjw.htmsblog.posts.dtos.CreatePostDTO;
import com.levyks.ifce.tjw.htmsblog.posts.services.CategoryService;
import com.levyks.ifce.tjw.htmsblog.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;

    @GetMapping
    public String index(Model model, PageRequestDTO pageRequest, @RequestHeader HttpHeaders headers) {
        var posts = HtmxUtils.getIfTargeted(headers, "posts-wrapper", () -> postService.getPage(pageRequest));
        var categories = HtmxUtils.getIfTargeted(headers, "categories-wrapper", () -> categoryService.getMostPopular(20));
        model.addAttribute("posts", posts);
        model.addAttribute("categories", categories);
        return "posts/index";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String create(Model model) {
        model.addAttribute("form", new CreatePostDTO());
        model.addAttribute("action", FormAction.CREATE);
        return "posts/form";
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

    @GetMapping("/{id}/edit")
    public String edit(Model model, Principal principal, @PathVariable Long id) {
        return postService.findById(id)
                .filter(post -> postService.canEdit(post, principal))
                .map(post -> {
                    model.addAttribute("form", CreatePostDTO.from(post));
                    model.addAttribute("action", FormAction.EDIT);
                    return "posts/form";
                })
                .orElse("redirect:/posts");
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String store(CreatePostDTO form) {
        postService.create(form);
        return "redirect:/posts";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, CreatePostDTO form, Principal principal) {
        return postService.findById(id)
                .filter(post -> postService.canEdit(post, principal))
                .map(post -> {
                    postService.update(post, form);
                    return "redirect:/posts/" + post.getSlug();
                })
                .orElse("redirect:/posts");
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Principal principal) {
        return postService.findById(id)
                .filter(post -> postService.canEdit(post, principal))
                .map(post -> {
                    postService.delete(post);
                    return "redirect:/posts";
                })
                .orElse("redirect:/posts");
    }
}
