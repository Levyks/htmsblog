package com.levyks.ifce.tjw.htmsblog.common.services;

public interface TemplateService {

    String currentUrlWithoutParameter(String key);

    String currentUrlWithParameter(String... params);
}
