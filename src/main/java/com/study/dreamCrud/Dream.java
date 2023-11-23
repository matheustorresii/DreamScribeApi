package com.study.dreamCrud;

import com.study.dreamCrud.dto.UpdateDream;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Instant;

@Entity
@Table(name = "dreams")
public class Dream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(name = "text", nullable = false, columnDefinition = "VARCHAR")
    private String text;
    @Column(name = "tags", nullable = false, columnDefinition = "VARCHAR")
    private String tags;
    @Column(name="type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DreamType type;
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP(6)")
    private final Instant createdAt = Instant.now();
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP(6)")
    private Instant updatedAt;

    public Dream() {
    }

    public Dream(final String text, final String tags, final DreamType type) {
        this.text = text;
        this.tags = tags;
        this.type = type;
        this.updatedAt = Instant.now();
    }

    public Dream update(final UpdateDream updateDream) {
        this.text = updateDream.text();
        this.tags = updateDream.tags();
        this.type = updateDream.type();
        this.updatedAt = Instant.now();
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTags() {
        return tags;
    }

    public DreamType getType() {
        return type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
