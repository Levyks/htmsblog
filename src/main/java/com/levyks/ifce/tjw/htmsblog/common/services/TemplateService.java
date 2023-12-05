package com.levyks.ifce.tjw.htmsblog.common.services;

public interface TemplateService {
    String currentUrlWithParameter(String key, String value);

    String currentUrlWithoutParameter(String key);
}
