package com.atcsimulator.app.modules.flight.entity;

import com.atcsimulator.app.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "flight")
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
public class Flight extends BaseEntity {
}
