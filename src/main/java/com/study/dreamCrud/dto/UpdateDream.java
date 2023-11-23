package com.study.dreamCrud.dto;

import com.study.dreamCrud.DreamType;

public record UpdateDream (
        String tags,
        String text,
        DreamType type
) {
}
