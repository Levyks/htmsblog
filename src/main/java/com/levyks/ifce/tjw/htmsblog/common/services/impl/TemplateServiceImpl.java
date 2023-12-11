package com.levyks.ifce.tjw.htmsblog.common.services.impl;

import com.levyks.ifce.tjw.htmsblog.common.services.TemplateService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService {
    @Override
    public String currentUrlWithoutParameter(String key) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replaceQueryParam(key)
                .toUriString();
    }

    @Override
    public String currentUrlWithParameter(String... params) {
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("Params must be even");
        }

        var builder = ServletUriComponentsBuilder.fromCurrentRequest();

        for (int i = 0; i < params.length; i += 2) {
            builder.replaceQueryParam(params[i], params[i + 1]);
        }

        return builder.toUriString();
    }
}
