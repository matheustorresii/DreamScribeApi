package com.study.dreamCrud.dto;

import com.study.dreamCrud.Dream;

public record DreamResponse(
        Long id,
        String tags,
        String text,
        String type,
        String created_at,
        String updated_at
) {
    public static DreamResponse from(final Dream dream){
        return new DreamResponse(
                dream.getId(),
                dream.getTags(),
                dream.getText(),
                dream.getType().name(),
                dream.getCreatedAt().toString(),
                dream.getUpdatedAt().toString()
        );
    }
}
