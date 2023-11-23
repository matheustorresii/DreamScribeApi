package com.study.dreamCrud.dto;

import com.study.dreamCrud.DreamType;

public record CreateDream(
        String tags,
        String text,
        DreamType type
) {
}
