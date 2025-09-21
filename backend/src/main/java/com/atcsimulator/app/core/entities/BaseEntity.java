package com.atcsimulator.app.core.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate(){
        Instant timestamp = Instant.now();
        this.createdAt = timestamp;
        this.updatedAt = timestamp;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = Instant.now();
    }
}
