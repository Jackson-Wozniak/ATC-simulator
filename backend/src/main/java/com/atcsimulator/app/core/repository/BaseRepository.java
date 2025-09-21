package com.atcsimulator.app.core.repository;

import com.atcsimulator.app.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
