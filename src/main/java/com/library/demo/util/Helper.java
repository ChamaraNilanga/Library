package com.library.demo.util;

import org.springframework.data.domain.Sort;

public class Helper {
    public static Sort getSortingProperty (String sortType, String sortBy) {
        if (!"DESC".equalsIgnoreCase(sortType.strip())) {
            return Sort.by(sortBy).ascending();
        }
        return Sort.by(sortBy).descending();
    }
}
