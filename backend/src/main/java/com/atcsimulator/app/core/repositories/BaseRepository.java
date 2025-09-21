package com.atcsimulator.app.core.repositories;

import com.atcsimulator.app.core.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
