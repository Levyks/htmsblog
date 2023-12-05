package com.levyks.ifce.tjw.htmsblog.common.utils;

import java.text.Normalizer;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SlugUtils {
    public static String generateSlug(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }

    public static <T> String generateUniqueSlug(Function<String, List<T>> findAllBySlugStartsWith, Function<T, String> getSlug, String title, Collection<String> reserved) {
        var slug = generateSlug(title);

        var existentSlugs = findAllBySlugStartsWith.apply(slug)
                .stream()
                .map(getSlug)
                .collect(Collectors.toSet());

        existentSlugs.addAll(reserved);

        if (existentSlugs.contains(slug)) {
            var i = 1;
            while (existentSlugs.contains(slug + "-" + i)) i++;
            return slug + "-" + i;
        }

        return slug;
    }

    public static <T> String generateUniqueSlug(Function<String, List<T>> findAllBySlugStartsWith, Function<T, String> getSlug, String title) {
        return generateUniqueSlug(findAllBySlugStartsWith, getSlug, title, List.of());
    }
}
