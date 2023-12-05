package com.levyks.ifce.tjw.htmsblog.common.services.impl;

import com.levyks.ifce.tjw.htmsblog.common.services.TemplateService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService {
    @Override
    public String currentUrlWithParameter(String key, String value) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replaceQueryParam(key, value)
                .toUriString();
    }

    @Override
    public String currentUrlWithoutParameter(String key) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replaceQueryParam(key)
                .toUriString();
    }
}
