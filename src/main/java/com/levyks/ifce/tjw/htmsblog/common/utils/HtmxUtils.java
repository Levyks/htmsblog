package com.levyks.ifce.tjw.htmsblog.common.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;

import java.util.function.Supplier;

public class HtmxUtils {
    @Nullable
    public static <T> T getIfTargeted(HttpHeaders headers, String target, Supplier<T> supplier) {
        var hxTarget = headers.getFirst("HX-Target");
        if (hxTarget != null && !hxTarget.equals(target)) return null;
        return supplier.get();
    }
}
