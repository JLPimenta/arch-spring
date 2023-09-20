package com.absentapp.project.domain.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false, length = 36)
    private String id;

    @Column(name = "DATA_CRIACAO", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
